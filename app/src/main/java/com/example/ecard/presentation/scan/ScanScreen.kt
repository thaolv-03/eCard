package com.example.ecard.presentation.scan


import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import com.example.ecard.R
import com.example.ecard.presentation.navigation.NavigationDestination
import com.example.ecard.presentation.AppViewModelProvider.Factory
import com.example.ecard.presentation.BottomBarEdit
import com.example.ecard.presentation.home.TopAppBarEdit
import com.journeyapps.barcodescanner.ScanContract

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

        Column(
            modifier = Modifier
                .padding(horizontal = 50.dp)
                .padding(top = 130.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.scanner),
                contentDescription = null,
                modifier = Modifier.size(230.dp)
            )

            Button(
                onClick = {
                    try {
                        scanLauncher.launch(scanViewModel.scanOptions)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                },
                modifier = Modifier.padding(top = 190.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.onSecondary
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = stringResource(R.string.click_scan_label),
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(horizontal = 45.dp, vertical = 3.dp)
                )
            }
        }
        Spacer(modifier = Modifier.padding(it))
    }
}

