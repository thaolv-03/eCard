package com.example.ecard.ui.scan

import android.content.ContentValues
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecard.R
import com.example.ecard.navigation.NavigationDestination
import com.example.ecard.ui.AppViewModelProvider.Factory
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import com.journeyapps.barcodescanner.ScanContract
import kotlinx.coroutines.launch
import java.lang.Exception

object ScanDestination : NavigationDestination {
    override val route = "scan"
    override val titleRes = R.string.app_name
}

@Composable
fun ScanScreen(
    scanViewModel: ScanViewModel = viewModel(factory = Factory),
    navigateTo: (String) -> Unit
) {
    val context = LocalContext.current
    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result ->
            if (result.contents == null) return@rememberLauncherForActivityResult
            scanViewModel.onScanSuccess(result.contents, navigateTo)
        }
    )
    LaunchedEffect(key1 = "", ) {
        try {
            scanLauncher.launch(scanViewModel.scanOptions)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    Text(
        text = "Hello !",
    )
}