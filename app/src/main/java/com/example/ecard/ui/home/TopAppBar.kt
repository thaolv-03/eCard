package com.example.ecard.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TopAppBarEdit(title: String) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h1,
                modifier = Modifier.fillMaxWidth().padding(end = 12.dp),
                textAlign = TextAlign.Center
            )
        },
        backgroundColor = MaterialTheme.colors.background,
    )
}

