package com.example.ecard.data.model

import androidx.compose.ui.graphics.painter.Painter

data class User(
    val name: String,
    val phone: String,
    val email: String,
    val birthday: String,
    val image: Painter,
    val social: Social
)

data class Social(
    val facebook: String,
    val instagram: String,
    val linkedIn: String,
    val tiktok: String,
    val youtube: String,
    val twitter: String
)