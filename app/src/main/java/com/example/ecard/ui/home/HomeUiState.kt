package com.example.ecard.ui.home

import androidx.compose.ui.graphics.painter.Painter
import com.example.ecard.data.model.Social

data class HomeUiState(
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val birthday: String = "",
    val image: String = "",
    val isMe: Boolean? = null,
    val socialList: List<Social>? = null
)