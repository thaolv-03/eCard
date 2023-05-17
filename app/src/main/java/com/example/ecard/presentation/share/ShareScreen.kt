package com.example.ecard.presentation.share

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import com.example.ecard.R
import com.example.ecard.presentation.navigation.NavigationDestination
import com.example.ecard.presentation.AppViewModelProvider.Factory
import com.example.ecard.presentation.BottomBarEdit
import com.example.ecard.presentation.home.TopAppBarEdit


object ShareDestination : NavigationDestination {
    override val route = "share"
    override val titleRes = R.string.app_name
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShareScreen(
    viewModel: ShareViewModel = viewModel(factory = Factory),
    currentDestination: NavDestination?,
    navigateTo: (String) -> Unit
) {
    val image = viewModel.image.value

    LaunchedEffect(key1 = "") {
        viewModel.createQr()
    }

    Scaffold(
        topBar = {
            TopAppBarEdit(title = R.string.share)
        },
        bottomBar = {
            BottomBarEdit(navigateTo = navigateTo, currentDestination = currentDestination)
        }
    ) {
        Column(modifier = Modifier.padding(horizontal = 32.dp).padding(top = 50.dp)) {
            Card(
                elevation = 5.dp,
                modifier = Modifier,

                backgroundColor = MaterialTheme.colors.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.share_profile),
                        style = MaterialTheme.typography.h5,
                    )
                    if (image != null) {
                        Image(
                            bitmap = image.asImageBitmap(),
                            contentDescription = ""
                        )
                    }
                }
            }
        }


    }

    Text(viewModel.text.value)
}