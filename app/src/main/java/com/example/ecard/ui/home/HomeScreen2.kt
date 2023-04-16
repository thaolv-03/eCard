package com.example.ecard.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecard.R
import com.example.ecard.ui.theme.ECardTheme
import com.example.ecard.ui.theme.Shapes


@Composable
fun PersonalInformation(image: Painter, content: String, modifier: Modifier = Modifier) {
    Card(
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface,
        modifier = modifier
    ) {
        Column() {
            val textStyle: TextStyle = MaterialTheme.typography.h5

            Row(
                modifier = Modifier.padding(10.dp),
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
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 19.dp)
                )
                Text(text = content, style = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onSurface))
            }
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 19.dp)
                )
                Text(text = content, style = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onSurface))
            }
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview() {
    ECardTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            PersonalInformation(
                image = painterResource(R.drawable.phone),
                content = stringResource(R.string.infor_phone),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }


}