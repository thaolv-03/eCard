package com.example.ecard.ui.edit

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecard.data.model.Social
import com.example.ecard.data.repository.OfflineSocialRepository
import com.example.ecard.data.repository.OfflineUserRepository
import com.example.ecard.data.repository.SocialRepository
import com.example.ecard.data.repository.UserRepository
import com.example.ecard.ui.home.HomeUiState
import com.example.ecard.ui.home.HomeViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EditViewModel(
    private val userRepository: UserRepository,
    private val socialRepository: SocialRepository
) : ViewModel() {

    val isPopUpSocialItem = mutableStateOf(false)
    val currentPickSocial: MutableState<Social?> = mutableStateOf(null)

    val uiState: StateFlow<EditUiState> =
        userRepository.getUserWithSocialList(0)
            .filterNotNull()
            .map {
                EditUiState(
                    userId = it.user.userId ?: 0,
                    name = it.user.name ?: "",
                    birthday = it.user.birthday ?: "",
                    phone = it.user.phone ?: "fsdfsd",
                    email = it.user.email ?: "",
                    image = it.user.image,
                    socialList = it.socialList
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(EditViewModel.TIMEOUT_MILLIS),
                initialValue = EditUiState()
            )

//    val uiState: MutableState<EditUiState> = mutableStateOf(EditUiState())

    init {
//        viewModelScope.launch {
////            uiState.value = inforFromDB.first()
//            inforFromDB.collect {
//                uiState.value = it
//            }
//        }


    }

    fun onPickSocialItem(socialId: Int) {
        currentPickSocial.value = uiState.value.socialList?.get(socialId)
        isPopUpSocialItem.value = true
    }

    fun onCancelOrDismissClickPopup() {
        isPopUpSocialItem.value = false
    }

    suspend fun onPopUpSocialItemSaveClick() {
        currentPickSocial.value?.also {
            socialRepository.updateSocial(it)
        }

        onCancelOrDismissClickPopup()
    }

    fun onPopUpSocialItemUserNameChange(newUserName: String) {
        currentPickSocial.value = currentPickSocial.value?.copy(userName = newUserName)

    }

    fun onPickSocialItemLinkChange(newLink: String) {
        currentPickSocial.value = currentPickSocial.value?.copy(link = newLink)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}