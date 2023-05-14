package com.example.ecard.ui.scan

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ScanScreen(scanViewModel: ScanViewModel = viewModel()) {
    val context = LocalContext.current
    Text(
        text = "Hello !",
    )
}