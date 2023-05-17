package com.example.ecard.presentation.edit

import com.example.ecard.data.model.Social
import com.example.ecard.data.model.User

data class EditUiState(
    val userId: Int = 0,
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val birthday: String = "",
    val image: String = "",
    val isMe: Boolean = true,
    val socialList: List<Social>? = null
)

fun EditUiState.toUser(editUiState: EditUiState) =
    User(
        userId = userId,
        name = name,
        phone = phone,
        email = email,
        birthday = birthday,
        isMe = isMe,
        imageUrl = image,
    )