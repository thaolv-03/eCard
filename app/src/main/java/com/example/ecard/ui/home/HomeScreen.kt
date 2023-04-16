package com.example.ecard.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.ecard.ui.theme.ECardTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.ecard.R
import androidx.compose.ui.unit.dp


@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "Hồ sơ",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                ) },
                backgroundColor = MaterialTheme.colors.background,
            )
        }
    ) {
        ImageAndName(
            image = painterResource(id = R.drawable.user),
            description = "",
            name = stringResource(id = R.string.infor_name1),
        modifier = Modifier.padding(it))
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
        )

        Text(
            text = name,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )
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