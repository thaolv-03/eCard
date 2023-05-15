package com.example.ecard.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.ecard.R

data class Contact(
    @DrawableRes val imageResourceId: Int,
    val name: String,
)

val contacts = listOf(
    Contact(R.drawable.avatar, "Le Van Thao"),
    Contact(R.drawable.avatar, "Vo The Luc"),
    Contact(R.drawable.avatar, "Tu Anh Dai"),
)
