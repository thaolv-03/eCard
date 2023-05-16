package com.example.ecard.ui.scan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecard.data.model.UserWithSocialList
import com.example.ecard.data.repository.SocialRepository
import com.example.ecard.data.repository.UserRepository
import com.example.ecard.ui.home.HomeDestination
import com.google.gson.Gson
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.coroutines.launch

class ScanViewModel(
    private val userRepository: UserRepository,
    private val socialRepository: SocialRepository
) : ViewModel() {

    val scanOptions = ScanOptions()
        .setOrientationLocked(false)
        .setPrompt("Đặt mã QR bên trong hình vuông để quét")
        .setDesiredBarcodeFormats(ScanOptions.QR_CODE)

    fun onScanSuccess(
        jsonString: String,
        navigateTo: (String) -> Unit
    ) {

        val gson = Gson()
        var data =
            try {
                gson.fromJson(jsonString, UserWithSocialList::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            } ?: return
        data = editData(data)

        viewModelScope.launch {
            val userId = userRepository.insertUser(
                data.user.apply { isMe = false }
            )
            data.socialList.forEach {
                it.apply {
                    this.userId = userId.toInt()
                }
                socialRepository.insertSocial(it)
            }

            navigateTo("${HomeDestination.route}/${userId}")
        }
    }

    private fun editData(data: UserWithSocialList): UserWithSocialList {
        return data.apply {
            user.userId = 0
            socialList.forEach {
                it.socialId = 0
            }
        }
    }
}