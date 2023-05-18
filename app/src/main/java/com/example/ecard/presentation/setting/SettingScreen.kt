package com.example.ecard.presentation.setting

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ecard.R
import com.example.ecard.presentation.navigation.NavigationDestination
import com.example.ecard.presentation.AppViewModelProvider
import com.example.ecard.presentation.BottomBarEdit
import com.example.ecard.presentation.home.TopAppBarEdit

object SettingDestination : NavigationDestination {
    override val route = "setting"
    override val titleRes = R.string.app_name
}

@Composable
fun SettingScreen(
    settingViewModel: SettingViewModel = viewModel(factory = AppViewModelProvider.Factory),
    currentDestination: NavDestination?,
    navigateTo: (String) -> Unit,
    onSignOut: () -> Unit
) {
    val context = LocalContext.current

    val gUser = settingViewModel.getGUser(context)

    Scaffold(
        topBar = {
            TopAppBarEdit(title = R.string.setting)
        },
        bottomBar = {
            BottomBarEdit(navigateTo = navigateTo, currentDestination = currentDestination)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            Card(
                modifier = Modifier
                    .padding(bottom = 8.dp),
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ProfileImage(gUser?.photoUrl)
                        ProfileInformation(gUser?.displayName ?: "")
                    }

                    SignOutButton(
                        {
                            settingViewModel.onSignOutButtonClick(context)
                            onSignOut()
                        })
                }
            }

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable { }
            ) {
                Column() {
                    Text(
                        text = stringResource(R.string.theme_title),
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Text(
                        text = stringResource(R.string.theme_content),
                        style = MaterialTheme.typography.body1
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column() {
                    Text(
                        text = stringResource(R.string.app_version_title),
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Text(
                        text = stringResource(R.string.app_version_content),
                        style = MaterialTheme.typography.body1
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column() {
                    Text(
                        text = stringResource(R.string.introduce_title),
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Text(
                        text = stringResource(R.string.introduce_content),
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Justify
                    )
                }
            }

        }

        Spacer(modifier = Modifier.padding(it))
    }
}

@Composable
fun ProfileImage(imageUrl: Uri?, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(imageUrl)
            .build(),
        contentDescription = "",
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(50)),
    )
}

@Composable
fun ProfileInformation(profileName: String, modifier: Modifier = Modifier) {
    Text(
        text = profileName,
        style = MaterialTheme.typography.h5,
        modifier = modifier.padding(start = 8.dp),
    )
}

@Composable
fun SignOutButton(
    onSignOutButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onSignOutButtonClick,
        modifier = modifier.padding(end = 10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.onSecondary
        )
    ) {
        Text(
            text = stringResource(id = R.string.sign_out),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}

@Composable
fun SettingInterface() {
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(false)
    ) {
//        Text(text = "abc")
    }
}