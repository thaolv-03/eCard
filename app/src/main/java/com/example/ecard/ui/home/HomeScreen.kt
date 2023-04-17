package com.example.ecard.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
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
import com.example.ecard.R
import com.example.ecard.data.model.Social
import com.example.ecard.data.model.SocialName
import com.example.ecard.ui.theme.ECardTheme

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel()
) {
    val uiState = homeViewModel.uiState.collectAsState()
    val user = uiState.value

    Scaffold(
        topBar = {
            TopAppBarEdit(title = "Hồ sơ")
        },
        bottomBar = {
            BottomBarEdit()
//            Text(text = "fsf", modifier = Modifier.height(100.dp))
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
                    image = painterResource(id = R.drawable.avatar),
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

        if (user.currentPickSocial != null) {
            Dialog(
                onDismissRequest = { homeViewModel.onCancelOrDismissClickPopup() },
                properties = DialogProperties(false)
            ) {
                SocialInforItemPopup(
                    social = user.currentPickSocial,
                    onCancelClick = { homeViewModel.onCancelOrDismissClickPopup() }
                )
            }
        }
    }
}

@Composable
fun ImageAndName(image: Painter, description: String, name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = description,
            modifier = Modifier
                .size(180.dp)
                .clip(RoundedCornerShape(50)),
            contentScale = ContentScale.Crop
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
                image = painterResource(R.drawable.phone), content = stringResource(
                    R.string.infor_phone
                )
            )
            PersonalInformationItem(
                image = painterResource(R.drawable.mail), content = stringResource(
                    R.string.infor_email
                )
            )
            PersonalInformationItem(
                image = painterResource(R.drawable.calendar), content = stringResource(
                    R.string.infor_birthday
                )
            )
        }
    }
}

@Composable
fun PersonalInformationItem(image: Painter, content: String) {
    val textStyle: TextStyle = MaterialTheme.typography.h4

    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = image,
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
    modifier: Modifier = Modifier
) {
    val image = when (social.socialName) {
        SocialName.FACEBOOK -> painterResource(id = R.drawable.facebook)
        SocialName.INSTAGRAM -> painterResource(id = R.drawable.instagram)
        SocialName.LINKEDIN -> painterResource(id = R.drawable.linkedin)
        SocialName.TIKTOK -> painterResource(id = R.drawable.tiktok)
        SocialName.YOUTUBE -> painterResource(id = R.drawable.youtube)
        SocialName.TWITTER -> painterResource(id = R.drawable.twitter)
    }

    Card(
        elevation = 5.dp,
        modifier = modifier
            .height(400.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            //        verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(10.dp)

        ) {
            Text(
                text = social.socialName.sName,
                style = MaterialTheme.typography.h2,
//                modifier = Modifier
//                    .padding(0.dp, 0.dp)
            )
            Image(
                painter = image,
                contentDescription = social.socialName.sName,
                modifier = Modifier
                    .size(110.dp)
                    .padding(0.dp, 10.dp)
            )
            Column(modifier = Modifier.padding(bottom = 10.dp)) {
                Row(
                    modifier = Modifier
                        .padding(5.dp, 10.dp)
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colors.surface)
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.user_name),
                        textAlign = TextAlign.Left,
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Normal,
                    )
                    Text(
                        text = social.userName,
                        textAlign = TextAlign.Right,
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = social.link,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
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
                    onClick = { /*TODO*/ },
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
            HomeScreen()
        }
    }
}

@Preview()
@Composable
fun Preview2() {
    ECardTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
//            SocialInforItemPopup(social = userExample.socialList[0])
        }
    }
}