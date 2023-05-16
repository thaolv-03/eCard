package com.example.ecard.ui.scan

import android.content.Context
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecard.data.model.UserWithSocialList
import com.example.ecard.data.repository.SocialRepository
import com.example.ecard.data.repository.UserRepository
import com.example.ecard.ui.home.HomeDestination
import com.google.gson.Gson
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.coroutines.launch

class ScanViewModel(
    private val userRepository: UserRepository,
    private val socialRepository: SocialRepository
) : ViewModel() {

    val scanOptions = ScanOptions().setDesiredBarcodeFormats(ScanOptions.QR_CODE).setBeepEnabled(false)
    fun onScanSuccess(jsonString: String, navigateTo: (String) -> Unit) {

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

            navigateTo(HomeDestination.route)
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