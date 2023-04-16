package com.example.ecard.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecard.R
import com.example.ecard.data.model.Social
import com.example.ecard.ui.theme.ECardTheme

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBarEdit(title = "Hồ sơ")
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageAndName(
                image = painterResource(id = R.drawable.user),
                description = "",
                name = stringResource(id = R.string.infor_name1),
                modifier = Modifier
                    .padding(it)
                    .padding(0.dp, 20.dp)
            )

            // Information
            PersonalInformation(
                modifier = Modifier
                    .padding(16.dp, 10.dp)
                    .width(300.dp)
            )

            SocialInforItem(
                socialImage = painterResource(id = R.drawable.user),
                socialName = stringResource(id = R.string.facebook),
                modifier = Modifier
                    .width(100.dp)
                    .padding(10.dp)
                )
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
                .size(200.dp)
                .clip(CircleShape)
                .padding(0.dp, 0.dp, 0.dp, 10.dp)
        )

        Text(
            text = name,
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun AddLinkedButton(text: String, modifier: Modifier = Modifier) {
//    Button(onClick = { /*TODO*/ }) {
//        Text(
//            text = text,
//            style = MaterialTheme.typography.h5
//        )
//    }
    Card(elevation = 5.dp) {
        TextButton(
            onClick = { /*TODO*/ },
            modifier = modifier
                .background(
                    MaterialTheme.colors.surface,
                    shape = MaterialTheme.shapes.small
                )
                .padding(5.dp)
        ) {
//            Card() {
            Text(
                text = text,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSurface
            )
//            }

        }
    }
}

@Composable
fun SocialInforItem(socialImage: Painter, socialName: String, modifier: Modifier = Modifier) {
    Card(
        elevation = 5.dp,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            Image(
                painter = socialImage,
                contentDescription = socialName,
                modifier = Modifier.size(50.dp)
            )
            Text(text = socialName)
        }
    }
}

@Preview
@Composable
fun Preview1() {
    ECardTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
//            ImageAndName(
//                image = painterResource(id = R.drawable.user),
//                description = "",
//                name = stringResource(id = R.string.infor_name1)
//            )
            HomeScreen()
        }
    }
}