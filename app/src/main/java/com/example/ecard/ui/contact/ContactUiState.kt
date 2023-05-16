package com.example.ecard.ui.contact

import com.example.ecard.data.model.SimpleUser

data  class ContactUiState(
    val searchKey: String = "",
    val userSimpleList: List<SimpleUser> = listOf()
)

