package com.example.ecard.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ecard.ECardApplication
import com.example.ecard.ui.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            HomeViewModel(
                eCardApplication().container.userRepository
            )
        }

    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.eCardApplication(): ECardApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ECardApplication)