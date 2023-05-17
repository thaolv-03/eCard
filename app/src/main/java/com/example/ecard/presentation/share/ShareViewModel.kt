package com.example.ecard.presentation.share

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecard.data.model.UserWithSocialList
import com.example.ecard.data.repository.UserRepository
import com.example.ecard.presentation.home.sortSocialList
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Hashtable


class ShareViewModel(private val userRepository: UserRepository) : ViewModel() {
    val text = mutableStateOf("")
    val image: MutableState<Bitmap?> = mutableStateOf(null)

    init {

    }

    fun createQr() {
        viewModelScope.launch {
            val hints = Hashtable<EncodeHintType, String>()
            hints[EncodeHintType.CHARACTER_SET] = "utf-8"
            val text = getData()
            val writer = MultiFormatWriter()
            try {
                val matrix = writer.encode(text, BarcodeFormat.QR_CODE, 800, 800, hints)
                val encoder = BarcodeEncoder()
                val bitmap = encoder.createBitmap(matrix)

                image.value = bitmap
            } catch (e: WriterException) {
                e.printStackTrace()
            }
        }
    }

    suspend fun getData(): String {
        val gson = Gson()
        val dataObject: UserWithSocialList =
            userRepository.getUserWithSocialList(1)
                .filterNotNull().first()
                .let {
                    it.copy(socialList = sortSocialList(it.socialList))
                }

        return try {
            gson.toJson(dataObject)
        } catch (e: Exception) {
            ""
        }
    }
}