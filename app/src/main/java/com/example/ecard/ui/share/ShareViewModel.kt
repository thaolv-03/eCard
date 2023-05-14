package com.example.ecard.ui.share

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecard.data.model.UserWithSocialList
import com.example.ecard.data.repository.UserRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

class ShareViewModel(private val userRepository: UserRepository) : ViewModel() {
    val text = mutableStateOf("")
    var dataObject: UserWithSocialList? = null

    init {
        viewModelScope.launch {
            userRepository.getUserWithSocialList(0).filterNotNull().collect() {
                text.value = it.user.name ?: "ddd"
            }
        }
    }

    suspend fun getData() {
        val gson = Gson()
        val dataJson = gson.toJson(dataObject)

//        text.value = dataObject?.user?.email ?: "ddd"
    }
}