package com.example.ecard.ui.share

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecard.R
import com.example.ecard.navigation.NavigationDestination
import com.example.ecard.ui.AppViewModelProvider.Factory


object ShareDestination : NavigationDestination {
    override val route = "share"
    override val titleRes = R.string.app_name
}

@Composable
fun ShareScreen(shareViewModel: ShareViewModel = viewModel(factory = Factory)) {

    LaunchedEffect(key1 = "") {
        shareViewModel.getData()
    }


    Text(shareViewModel.text.value)
}