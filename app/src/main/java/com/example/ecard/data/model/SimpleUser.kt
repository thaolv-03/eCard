package com.example.ecard.data.model

import androidx.annotation.DrawableRes
import com.example.ecard.R

data class SimpleUser(
    val userId: Int = 1,
    val isMe: Boolean = true,
    val name: String = "",
    val image: String = ""
)

