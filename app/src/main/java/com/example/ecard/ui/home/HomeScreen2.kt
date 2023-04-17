package com.example.ecard.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecard.R
import com.example.ecard.ui.theme.ECardTheme
import com.example.ecard.ui.theme.Shapes


@Composable
fun PersonalInformation(modifier: Modifier = Modifier) {
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview() {
    ECardTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            PersonalInformation(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }


}