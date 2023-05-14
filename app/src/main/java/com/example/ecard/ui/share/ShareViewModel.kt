package com.example.ecard.ui.share

import android.graphics.Bitmap
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecard.data.model.UserWithSocialList
import com.example.ecard.data.repository.UserRepository
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

class ShareViewModel(private val userRepository: UserRepository) : ViewModel() {
    val text = mutableStateOf("")
    val image: MutableState<Bitmap?> = mutableStateOf(null)
//    var dataObject: UserWithSocialList? = null

    init {
//        viewModelScope.launch {
//            userRepository.getUserWithSocialList(0).filterNotNull().collect() {
//                dataObject = it
//            }
//        }
    }

    fun createQr() {
        viewModelScope.launch {
            val text = getData()
            val writer = MultiFormatWriter()
            try {
                val matrix = writer.encode(text, BarcodeFormat.QR_CODE, 1500, 1500)
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
            userRepository.getUserWithSocialList(0).filterNotNull().first()

        return try {
            gson.toJson(dataObject)
        } catch (e: Exception) {
            ""
        }
    }
}