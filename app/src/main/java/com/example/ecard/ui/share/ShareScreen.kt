package com.example.ecard.ui.share

import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecard.R
import com.example.ecard.navigation.NavigationDestination
import com.example.ecard.ui.AppViewModelProvider.Factory


object ShareDestination : NavigationDestination {
    override val route = "share"
    override val titleRes = R.string.app_name
}

@Composable
fun ShareScreen(viewModel: ShareViewModel = viewModel(factory = Factory)) {
    val image = viewModel.image.value

    LaunchedEffect(key1 = "") {
        viewModel.createQr()
    }

    if (image != null) {
        Image(
            bitmap = image.asImageBitmap(),
            contentDescription = ""
        )
    }




    Text(viewModel.text.value)
}