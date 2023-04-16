package com.example.ecard.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ecard.R

@Composable
fun PersonalInformation(image: Painter, content: String, modifier: Modifier = Modifier) {
    Column() {
        Row() {
            Image(painter = painterResource(R.drawable.phone), contentDescription = null)
            Text(text = stringResource(R.string.infor_phone))
        }
    }
}
