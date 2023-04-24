package com.example.ecard.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecard.data.dataResource
import com.example.ecard.data.model.Social
import com.example.ecard.data.model.User
import com.example.ecard.data.repository.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class HomeViewModel(userRepository: UserRepository) : ViewModel() {

    private val user: User = User()

    val isPopUpSocialItem = mutableStateOf(false)
    val currentPickSocial:MutableState<Social?> = mutableStateOf(null)
    init {
        viewModelScope.launch {
            userRepository.insertUser(dataResource.userExample)

        }
    }

//    val uiState = MutableStateFlow(
//        HomeUiState(
//            name = user.name ?: "",
//            birthday = user.birthday ?: "",
//            phone = user.phone ?: "",
//            email = user.email ?: "",
//            image = user.image,
//        )
//    )
    val uiState: StateFlow<HomeUiState> =
        userRepository.getUserWithSocialList(0)
            .filterNotNull()
            .map {
                HomeUiState(
                    name = it.user.name ?: "",
                    birthday = it.user.birthday ?: "",
                    phone = it.user.phone ?: "",
                    email = it.user.email ?: "",
                    image = it.user.image,
                    socialList = it.socialList
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    fun onPickSocialItem(socialId: Int) {
//        uiState.update {
//            it.copy(currentPickSocial = user.socialList[socialId])
//        }
//        uiState.value = uiState.value.copy(currentPickSocial = user.socialList[socialId])
        currentPickSocial.value = uiState.value.socialList?.get(socialId)
        isPopUpSocialItem.value = true
    }

    fun onCancelOrDismissClickPopup() {
//        currentPickSocial.value = null
        isPopUpSocialItem.value = false
    }

    fun onClickAccess(context: Context, url: String) {
        val urlEdit =
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            "http://$url"
        } else url

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlEdit))
        startActivity(context, browserIntent, null)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}