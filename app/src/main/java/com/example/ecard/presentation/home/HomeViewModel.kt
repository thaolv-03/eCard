package com.example.ecard.presentation.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.telephony.PhoneNumberUtils
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecard.data.dataResource
import com.example.ecard.data.model.Social
import com.example.ecard.data.repository.UserRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.flow.*


class HomeViewModel(
    savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository,
) : ViewModel() {

    private val userId: Int = savedStateHandle[HomeDestination.userIdArg] ?: 1

    val isPopUpSocialItem = mutableStateOf(false)
    val currentPickSocial: MutableState<Social?> = mutableStateOf(null)

    init {
    }

    fun getGUser(context: Context): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(context)
    }

    val uiState: StateFlow<HomeUiState> =
        userRepository.getUserWithSocialList(userId)
            .filterNotNull()
            .map {
                HomeUiState(
                    name = it.user.name ?: "",
                    birthday = it.user.birthday ?: "",
                    phone = PhoneNumberUtils.formatNumber(it.user.phone ?: "", "VN"),
                    email = it.user.email ?: "",
                    image = it.user.imageUrl ?: "",
                    isMe = it.user.isMe,
                    socialList =
                    if (it.socialList.isNotEmpty()) sortSocialList(it.socialList)
                    else dataResource.userExample.socialList
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    fun onPickSocialItem(socialTypeId: Int) {
        currentPickSocial.value = uiState.value.socialList?.get(socialTypeId)
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

fun sortSocialList(socialList: List<Social>): List<Social> {
    val result = mutableListOf<Social>()

    for (i in 0..5) {
        val social = socialList.firstOrNull() {
            it.socialTypeId == i
        }

        if (social != null) {
            result.add(social)
        }
    }
    return result
}