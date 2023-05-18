package com.example.ecard.presentation

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ecard.app.ECardApplication
import com.example.ecard.presentation.contact.ContactViewModel
import com.example.ecard.presentation.edit.EditViewModel
import com.example.ecard.presentation.home.HomeViewModel
import com.example.ecard.presentation.scan.ScanViewModel
import com.example.ecard.presentation.setting.SettingViewModel
import com.example.ecard.presentation.share.ShareViewModel
import com.example.ecard.presentation.sign_in.SignInViewModel
import com.example.ecard.data.repository.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            SignInViewModel(
                AuthRepositoryImpl(FirebaseAuth.getInstance()),
                eCardApplication().container.userRepository
            )
        }

        // Initializer for ItemEditViewModel
        initializer {
            HomeViewModel(
                this.createSavedStateHandle(),
                eCardApplication().container.userRepository
            )
        }

        initializer {
            ContactViewModel(
                eCardApplication().container.userRepository,
                eCardApplication().container.socialRepository
            )
        }

        initializer {
            ScanViewModel(
                eCardApplication().container.userRepository,
                eCardApplication().container.socialRepository
            )
        }

        initializer {
            EditViewModel(
                eCardApplication().container.userRepository,
                eCardApplication().container.socialRepository
            )
        }

        initializer {
            ShareViewModel(
                eCardApplication().container.userRepository
            )
        }

        initializer {
            SettingViewModel(
                eCardApplication().container.userRepository,
                Firebase.auth
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
