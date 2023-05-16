package com.example.ecard.ui.setting

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.ecard.data.repository.UserRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth

class SettingViewModel(
    private val userRepository: UserRepository,
    private val auth: FirebaseAuth,
) : ViewModel() {


    fun getGUser(context: Context): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(context)
    }

    fun onSignOutButtonClick() {
        auth.signOut()
    }

}