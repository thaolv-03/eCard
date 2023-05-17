package com.example.ecard.presentation.edit

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecard.data.dataResource
import com.example.ecard.data.model.Social
import com.example.ecard.data.model.User
import com.example.ecard.data.repository.SocialRepository
import com.example.ecard.data.repository.UserRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class EditViewModel(
    private val userRepository: UserRepository,
    private val socialRepository: SocialRepository,
) : ViewModel() {

    val isPopUpSocialItem = mutableStateOf(false)
    val currentPickSocial: MutableState<Social?> = mutableStateOf(null)

    val isPopUpInforItem = mutableStateOf(false)
    val currentPickInfor: MutableState<User?> = mutableStateOf(null)

    fun getGUser(context: Context): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(context)
    }

    val uiState: StateFlow<EditUiState> =
        userRepository.getUserWithSocialList(1)
            .filterNotNull()
            .map {
                EditUiState(
                    userId = it.user.userId ?: 0,
                    name = it.user.name ?: "",
                    birthday = it.user.birthday ?: "",
                    phone = it.user.phone ?: "fsdfsd",
                    email = it.user.email ?: "",
                    image = it.user.imageUrl ?: "",
                    isMe = it.user.isMe ?: true,
                    socialList =
                    if (it.socialList.isNotEmpty()) sortSocialList(it.socialList)
                    else dataResource.userExample.socialList
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = EditUiState()
            )

    init {
//        viewModelScope.launch {
//            dataResource.userExample.socialList.forEachIndexed { i, social ->
//                socialRepository.insertSocial(social.copy(socialId = i + 1))
//            }
//        }
    }

    // Social
    fun onPickSocialItem(socialTypeId: Int) {
        currentPickSocial.value = uiState.value.socialList?.get(socialTypeId)
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

    // Information
    fun onPickInforItem() {
        currentPickInfor.value = uiState.value.let {
            it.toUser(it)
        }
        isPopUpInforItem.value = true
    }

    fun onCancelOrDismissClickInforPopup() {
        isPopUpInforItem.value = false
    }

    suspend fun onPopUpInforItemSaveClick() {
        currentPickInfor.value?.also {
            userRepository.updateUser(it)
        }

        onCancelOrDismissClickInforPopup()
    }

    fun onPopUpInforItemNameChange(newName: String) {
        currentPickInfor.value = currentPickInfor.value?.copy(name = newName)
    }

    fun onPopUpInforItemPhoneChange(newPhone: String) {
        currentPickInfor.value = currentPickInfor.value?.copy(phone = newPhone)
    }

    fun onPopUpInforItemEmailChange(newEmail: String) {
        currentPickInfor.value = currentPickInfor.value?.copy(email = newEmail)
    }

    fun onPopUpInforItemBirthdayChange(newBirthday: LocalDate) {
        val newBirthdayString = localDateToDateString(newBirthday)

        currentPickInfor.value = currentPickInfor.value?.copy(birthday = newBirthdayString)
    }


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

fun dateStringToLocalDate(dateString: String): LocalDate {
    val dateArr = dateString.split("/")
    val day = dateArr[0].toInt()
    val month = dateArr[1].toInt()
    val year = dateArr[2].toInt()

    return LocalDate.of(year, month, day)
}

fun localDateToDateString(localDate: LocalDate): String {
    val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return localDate.format(dateFormat)
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