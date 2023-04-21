package com.example.ecard.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecard.data.model.User
import com.example.ecard.data.repository.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class HomeViewModel(userRepository: UserRepository) : ViewModel() {

    private val user: User = User()

    val isPopUpSocialItem = mutableStateOf(false)

    init {
        viewModelScope.launch {
//            user =
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
        userRepository.getUserStream(0)
            .filterNotNull()
            .map {
                HomeUiState(
                    name = it.name ?: "",
                    birthday = it.birthday ?: "",
                    phone = it.phone ?: "",
                    email = it.email ?: "",
                    image = it.image,
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
    }

    fun onCancelOrDismissClickPopup() {
//        uiState.update {
//            it.copy(currentPickSocial = null)
//        }
    }

    fun onClickAccess(context: Context, url: String) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            "http://$url"
        }


        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(context, browserIntent, null)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}