package com.example.ecard.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ecard.data.dataResource.userExample
import com.example.ecard.data.model.Social
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    //TO DO: get user from Repository
    private val user = userExample

    val uiState = MutableStateFlow(
        HomeUiState(
            name = user.name,
            birthday = user.birthday,
            phone = user.phone,
            email = user.email,
            image = user.image,
        )
    )

    fun onPickSocialItem(socialId: Int) {
        uiState.update {
            it.copy(currentPickSocial = user.socialList[socialId])
        }

    }

    fun onCancelOrDismissClickPopup() {
        uiState.update {
            it.copy(currentPickSocial = null)
        }
    }

}