package com.example.ecard.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.ecard.ui.theme.ECardTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.ecard.R

@Composable
fun ImageAndName(image: Painter, description: String, name: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Image(
            painter = image,
            contentDescription = description,
            modifier = Modifier
                .clip(CircleShape)
        )

        Text(
            text = name,
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center
        )
    }

}

@Preview
@Composable
fun Preview1() {
    ECardTheme() {
        Surface() {
//            ImageAndName(image = , description = , name = )
        }
    }
}


@Composable
fun PersonalInformation(image: Painter, content: String, modifier: Modifier = Modifier) {
    Column() {
        Row() {
            Image(painter = painterResource(R.drawable.phone), contentDescription = null)
            Text(text = stringResource(R.string.infor_phone))
        }
    }
}

