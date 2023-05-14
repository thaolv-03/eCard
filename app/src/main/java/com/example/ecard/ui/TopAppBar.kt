package com.example.ecard.ui.home

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ecard.R
import com.example.ecard.ui.edit.EditDestination

@Composable
fun TopAppBarEdit(
    title: Int,
    actionButton: @Composable (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.h1,
                modifier = if (actionButton == null) Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp)
                else Modifier.fillMaxWidth().padding(start = 50.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onSecondary
            )
        },
        actions = {
            if (actionButton != null) {
                actionButton()
            }
        },
        backgroundColor = MaterialTheme.colors.background,
    )
}

