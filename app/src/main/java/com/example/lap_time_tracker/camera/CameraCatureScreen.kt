package com.example.lap_time_tracker.camera

import android.net.Uri
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import java.io.File
import java.util.concurrent.Executors
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import android.os.Handler
import android.os.Looper



@Composable
fun CameraCaptureScreen(
    navController: NavController,
    onPhotoCaptured: (String) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current


    //Camera Permission
    val cameraPermission = Manifest.permission.CAMERA
    val permissionGranted = ContextCompat.checkSelfPermission(
        context,
        cameraPermission
    ) == PackageManager.PERMISSION_GRANTED

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            if (!granted) {
                Toast.makeText(context, "Camera permission is required", Toast.LENGTH_SHORT).show()
            }
        }
    )

    LaunchedEffect(Unit) {
        if (!permissionGranted) launcher.launch(cameraPermission)
    }

    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    var imageCapture by remember { mutableStateOf<ImageCapture?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔲 SQUARE CAMERA PREVIEW
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f), // keeps perfect square
            factory = { ctx ->
                val previewView = PreviewView(ctx)

                cameraProviderFuture.addListener({
                    val cameraProvider = cameraProviderFuture.get()

                    val preview = Preview.Builder().build().apply {
                        surfaceProvider = previewView.surfaceProvider
                    }

                    imageCapture = ImageCapture.Builder().build()

                    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageCapture
                    )

                }, ContextCompat.getMainExecutor(ctx))

                previewView
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 📸 CAPTURE BUTTON (NOW VISIBLE!)
        ElevatedButton(
            onClick = {
                val output = File(context.externalCacheDir, "${System.currentTimeMillis()}.jpg")
                val outputOptions = ImageCapture.OutputFileOptions.Builder(output).build()

                imageCapture?.takePicture(
                    outputOptions,
                    Executors.newSingleThreadExecutor(),
                    object : ImageCapture.OnImageSavedCallback {
                        override fun onError(exc: ImageCaptureException) {
                            exc.printStackTrace()
                        }

                        override fun onImageSaved(result: ImageCapture.OutputFileResults) {

                            val uriString = output.toURI().toString()

                            Handler(Looper.getMainLooper()).post {
                                onPhotoCaptured(uriString)
                                navController.popBackStack()
                            }

                        }
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp), // ensures button height
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5C5470),
                contentColor = Color.White
            )
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Capture"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Capture Photo")
        }
    }
}



