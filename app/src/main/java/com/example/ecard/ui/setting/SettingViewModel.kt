package com.example.ecard.ui.setting

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.ecard.data.repository.UserRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import java.security.AccessController.getContext


class SettingViewModel(
    private val userRepository: UserRepository,
    private val auth: FirebaseAuth,
) : ViewModel() {


    fun getGUser(context: Context): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(context)
    }

    fun onSignOutButtonClick(context: Context) {
        auth.signOut()
        GoogleSignIn.getClient(
            context,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        ).signOut()
    }

}