package com.example.ecard.ui.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.PhoneInTalk
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ecard.R
import com.example.ecard.data.model.CONST
import com.example.ecard.data.model.Social
import com.example.ecard.data.model.SocialName
import com.example.ecard.navigation.NavigationDestination
import com.example.ecard.ui.AppViewModelProvider.Factory
import com.example.ecard.ui.BottomBarEdit
import com.example.ecard.ui.edit.EditDestination
import com.example.ecard.ui.theme.ECardTheme

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
    const val userIdArg = "userId"
    val routeWithArgs = "$route/{$userIdArg}"
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(factory = Factory),
    currentDestination: NavDestination?,
    navigateTo: (String) -> Unit
) {
    val uiState = homeViewModel.uiState.collectAsState()
    val user = uiState.value

    Scaffold(
        topBar = {
            TopAppBarEdit(
                title = R.string.home_screen,
                actionButton = if (user.isMe == true) {
                    { ActionEditButton(navigateTo = navigateTo) }
                } else null
            )
        },
        bottomBar = {
            BottomBarEdit(navigateTo = navigateTo, currentDestination = currentDestination)
        }
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                ImageAndName(
                    imageUrl = user.image,
                    description = "",
                    name = user.name,
                    modifier = Modifier
                        .padding(0.dp, 20.dp)
                )

                PersonalInformation(
                    email = user.email,
                    phone = user.phone,
                    birthday = user.birthday,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ) {
                        SocialInforItem(
                            socialImage = painterResource(id = R.drawable.facebook),
                            socialName = stringResource(id = R.string.facebook),
                            modifier = Modifier
                                .width(100.dp)
                                .clickable { homeViewModel.onPickSocialItem(0) }
                        )
                        SocialInforItem(
                            socialImage = painterResource(id = R.drawable.instagram),
                            socialName = stringResource(id = R.string.instagram),
                            modifier = Modifier
                                .width(100.dp)
                                .clickable { homeViewModel.onPickSocialItem(1) }
                        )
                        SocialInforItem(
                            socialImage = painterResource(id = R.drawable.tiktok),
                            socialName = stringResource(id = R.string.tiktok),
                            modifier = Modifier
                                .width(100.dp)
                                .clickable { homeViewModel.onPickSocialItem(2) }
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ) {
                        SocialInforItem(
                            socialImage = painterResource(id = R.drawable.youtube),
                            socialName = stringResource(id = R.string.youtube),
                            modifier = Modifier
                                .width(100.dp)
                                .clickable { homeViewModel.onPickSocialItem(3) }
                        )
                        SocialInforItem(
                            socialImage = painterResource(id = R.drawable.linkedin),
                            socialName = stringResource(id = R.string.linkedIn),
                            modifier = Modifier
                                .width(100.dp)
                                .clickable { homeViewModel.onPickSocialItem(4) }
                        )
                        SocialInforItem(
                            socialImage = painterResource(id = R.drawable.twitter),
                            socialName = stringResource(id = R.string.twitter),
                            modifier = Modifier
                                .width(100.dp)
                                .clickable { homeViewModel.onPickSocialItem(5) }
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(it))
            }

        }


        if (homeViewModel.isPopUpSocialItem.value) {
            Dialog(
                onDismissRequest = { homeViewModel.onCancelOrDismissClickPopup() },
                properties = DialogProperties(false)
            ) {
                SocialInforItemPopup(
                    social = homeViewModel.currentPickSocial.value!!,
                    onCancelClick = { homeViewModel.onCancelOrDismissClickPopup() },
                    onAccessClick = { context, url ->
                        homeViewModel.onClickAccess(context, url)
                    }
                )
            }
        }
    }
}


@Composable
fun ActionEditButton(navigateTo: (String) -> Unit) {
    Button(onClick = { navigateTo(EditDestination.route) }) {
        Icon(
            imageVector = Icons.Outlined.EditNote,
            contentDescription = stringResource(
                id = R.string.edit
            )
        )
    }

}

@Composable
fun ImageAndName(imageUrl: String, description: String, name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Image(
//            painter = image,
//            contentDescription = description,
//            modifier = Modifier
//                .size(180.dp)
//                .clip(RoundedCornerShape(50)),
//            contentScale = ContentScale.Crop
//        )

        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(imageUrl)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .size(180.dp)
                .clip(RoundedCornerShape(50)),
        )

        Text(
            text = name,
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Composable
fun PersonalInformation(
    phone: String,
    email: String,
    birthday: String,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = 5.dp,
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.surface,
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(vertical = 5.dp)) {
            PersonalInformationItem(
                image = Icons.Outlined.PhoneInTalk, content = phone
            )
            PersonalInformationItem(
                image = Icons.Outlined.Mail, content = email

            )
            PersonalInformationItem(
                image = Icons.Outlined.Event, content = birthday
            )
        }
    }
}

@Composable
fun PersonalInformationItem(image: ImageVector, content: String) {
    val textStyle: TextStyle = MaterialTheme.typography.h4

    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = image,
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .padding(end = 19.dp)
        )
        Text(text = content, style = textStyle)
    }
}

@Composable
fun SocialInforItem(
    socialImage: Painter,
    socialName: String,
//    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = 5.dp,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(5.dp)
        ) {
            Image(
                painter = socialImage,
                contentDescription = socialName,
                modifier = Modifier
                    .size(60.dp)
                    .padding(5.dp)
            )
            Text(
                text = socialName,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 3.dp)
            )
        }
    }
}

@Composable
fun SocialInforItemPopup(
    social: Social,
    onCancelClick: () -> Unit,
    onAccessClick: (Context, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val image = when (CONST.SOCIAL_TYPE[social.socialTypeId]) {
        SocialName.FACEBOOK.sName -> painterResource(id = R.drawable.facebook)
        SocialName.INSTAGRAM.sName -> painterResource(id = R.drawable.instagram)
        SocialName.LINKEDIN.sName -> painterResource(id = R.drawable.linkedin)
        SocialName.TIKTOK.sName -> painterResource(id = R.drawable.tiktok)
        SocialName.YOUTUBE.sName -> painterResource(id = R.drawable.youtube)
        else -> painterResource(id = R.drawable.twitter)
    }

    Card(
        elevation = 5.dp,
        modifier = modifier.height(IntrinsicSize.Min),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = CONST.SOCIAL_TYPE[social.socialTypeId] as String,
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(vertical = 5.dp)
            )
            Image(
                painter = image,
                contentDescription = CONST.SOCIAL_TYPE[social.socialTypeId] as String,
                modifier = Modifier
                    .size(110.dp)
                    .padding(0.dp, 10.dp)
            )
            Column(modifier = Modifier.padding(bottom = 10.dp)) {
                Text(
                    text = social.userName,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 5.dp)
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colors.surface)
                        .padding(15.dp)
                )

                Text(
                    text = social.link,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 5.dp)
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colors.surface)
                        .padding(15.dp),
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp)
                    .padding(bottom = 10.dp)
            ) {
                Button(
                    onClick = onCancelClick,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        style = MaterialTheme.typography.h6,
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                Button(
                    onClick = { onAccessClick(context, social.link) },
                    modifier = Modifier
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.onSecondary
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.access),
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.secondary
                    )
                }
            }
        }
    }
}

@Preview()
@Composable
fun HomeScreenPreview() {
    ECardTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeScreen(navigateTo = {}, currentDestination = null)
        }
    }
}