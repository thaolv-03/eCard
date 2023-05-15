package com.example.ecard.ui.scan

import android.content.ContentValues
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import com.example.ecard.R
import com.example.ecard.navigation.NavigationDestination
import com.example.ecard.ui.AppViewModelProvider.Factory
import com.example.ecard.ui.BottomBarEdit
import com.example.ecard.ui.home.TopAppBarEdit
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
    currentDestination: NavDestination?,
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
    
    Scaffold(
        topBar = {
            TopAppBarEdit(title = R.string.scan)
        },
        bottomBar = {
            BottomBarEdit(navigateTo = navigateTo, currentDestination = currentDestination)
        }
    ) {

        LaunchedEffect(key1 = "", ) {
            try {
                scanLauncher.launch(scanViewModel.scanOptions)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        Spacer(modifier = Modifier.padding(it))
    }
}