package com.example.ecard.data.model

import androidx.annotation.DrawableRes
import com.example.ecard.R

data class SimpleUser(
    val userId: Int = 1,
    val name: String = "",
    val image: String = ""
)

val simpleUsers = listOf(
    SimpleUser(1, "Le Van Thao", "R.drawable.avatar"),
    SimpleUser(1, "Vo The Luc", "R.drawable.avatar"),
    SimpleUser(1, "Tu Anh Dai", "R.drawable.avatar"),
)
