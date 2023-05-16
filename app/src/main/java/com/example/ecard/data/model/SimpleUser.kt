package com.example.ecard.data.model

import androidx.annotation.DrawableRes
import com.example.ecard.R

data class SimpleUser(
    val name: String = "",
    val image: String = ""
)

val simpleUsers = listOf(
    SimpleUser("Le Van Thao", "R.drawable.avatar"),
    SimpleUser("Vo The Luc", "R.drawable.avatar"),
    SimpleUser("Tu Anh Dai", "R.drawable.avatar"),
)
