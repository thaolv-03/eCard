package com.example.ecard.ui.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import com.example.ecard.R
import com.example.ecard.data.model.simpleUsers
import com.example.ecard.navigation.NavigationDestination
import com.example.ecard.ui.AppViewModelProvider
import com.example.ecard.ui.BottomBarEdit
import com.example.ecard.ui.home.SocialInforItemPopup
import com.example.ecard.ui.home.TopAppBarEdit

object SettingDestination : NavigationDestination {
    override val route = "setting"
    override val titleRes = R.string.app_name
}

@Composable
fun SettingScreen(
    settingViewModel: SettingViewModel = viewModel(factory = AppViewModelProvider.Factory),
    currentDestination: NavDestination?,
    navigateTo: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarEdit(title = R.string.setting)
        },
        bottomBar = {
            BottomBarEdit(navigateTo = navigateTo, currentDestination = currentDestination)
        }
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {

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
                        ProfileImage(simpleUsers[1].image)
                        ProfileInformation(simpleUsers[1].name)
                    }

                    SignOutButton()
                }
            }

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable {  }
            ) {
                Column() {
                    Text(
                        text = "Giao diện",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Text(
                        text = "Theo chế độ mặc định của hệ thống",
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
                        text = "Phiên bản ứng dụng",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Text(
                        text = "eCard 1.0",
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
                        text = "Giới thiệu về ứng dụng",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Text(
                        text = "Ứng dụng là đóng vai trò là một danh thiếp mobile giúp chia sẻ thông tin cá nhân như họ tên, số điện thoại, email, các liên kết mạng xã hội (Facebook, Instagram, LinkedIn,...) với các thiết bị khác",
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
fun ProfileImage(imageUrl: String, modifier: Modifier = Modifier) {
//    AsyncImage(
//        model = ImageRequest.Builder(context = LocalContext.current)
//            .data(imageUrl)
//            .build(),
//        contentDescription = "",
//        contentScale = ContentScale.FillBounds,
//        modifier = modifier
//            .size(64.dp)
//            .padding(8.dp)
//            .clip(RoundedCornerShape(50)),
//    )

    Image(
        painter = painterResource(id = R.drawable.avatar),
        contentDescription = null,
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
fun SignOutButton(modifier: Modifier = Modifier) {
    Button(
        onClick = { /*TODO*/ },
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
        onDismissRequest = {  },
        properties = DialogProperties(false)
    ) {
        Text(text = "abc")
    }
}