package com.example.ecard.ui.contact

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecard.data.model.SimpleUser
import com.example.ecard.data.model.User
import com.example.ecard.data.repository.SocialRepository
import com.example.ecard.data.repository.UserRepository
import kotlinx.coroutines.Delay
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ContactViewModel(
    private val userRepository: UserRepository,
    private val socialRepository: SocialRepository
) : ViewModel() {
    val uiState = mutableStateOf(ContactUiState())

    var dataFromDB = listOf<SimpleUser>()


    init {
        viewModelScope.launch {
            val dataStream = userRepository.getAllUserStream()
                .filterNotNull()
                .map { users ->
                    users.map {
                        SimpleUser(
                            userId = it.userId,
                            isMe = it.isMe ?: false,
                            name = it.name ?: "",
                            image = it.imageUrl ?: ""
                        )
                    }
                }
            uiState.value = uiState.value.copy(userSimpleList = dataStream.first())

            dataStream.collect() {
                dataFromDB = it
            }
        }
    }

    fun onSearchKeyChange(newSearchKey: String) {
        updateUiState(newSearchKey)
    }

    fun updateUiState(searchKey: String) {
        uiState.value = ContactUiState(
            searchKey,
            dataFromDB.filter {
                it.name.contains(searchKey, true)
            })
//        uiState.value = uiState.value.copy(
//            userSimpleList = dataFromDB.filter {
//                it.name.contains(searchKey, true)
//            }
//        )
    }

    fun onDeleteButtonClick(userId: Int) {
        val searchKey = uiState.value.searchKey

        viewModelScope.launch {
            socialRepository.deleteByUserId(userId)
            userRepository.deleteUser(User(userId = userId))

            uiState.value = uiState.value.copy(
                userSimpleList = dataFromDB
            )
        }
    }
}
