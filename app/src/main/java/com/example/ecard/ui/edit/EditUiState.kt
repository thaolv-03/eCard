package com.example.ecard.ui.edit

import androidx.compose.ui.graphics.painter.Painter
import com.example.ecard.data.model.Social

data class EditUiState(
    val userId: Int = 0,
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val birthday: String = "",
    val image: Painter? = null,
    val socialList: List<Social>? = null
)