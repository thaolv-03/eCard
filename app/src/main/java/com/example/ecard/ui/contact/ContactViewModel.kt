package com.example.ecard.ui.contact

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecard.data.model.SimpleUser
import com.example.ecard.data.repository.UserRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ContactViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    val uiState = mutableStateOf(ContactUiState())

    var dataFromDB = listOf<SimpleUser>()

    init {
        viewModelScope.launch {
            val data = userRepository.getAllUserStream()
                .filterNotNull()
                .map { users ->
                    users.map {
                        SimpleUser(
                            name = it.name ?: "",
                            image = it.imageUrl ?: ""
                        )
                    }
                }

            uiState.value = uiState.value.copy(userSimpleList = data.first())

            data.collect() {
                dataFromDB = it
            }
        }
    }

    fun onSearchKeyChange(newSearchKey: String) {
        uiState.value = ContactUiState(newSearchKey)
        viewModelScope.launch {
            uiState.value = uiState.value.copy(
                userSimpleList = dataFromDB.filter {
                    it.name.contains(newSearchKey, true)
                }
            )
        }

    }

}
