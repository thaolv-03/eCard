package com.example.ecard.ui.sign_in

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecard.R
import com.example.ecard.data.repository.UserRepository
import com.example.firebaseauthyt.data.AuthRepository
import com.example.firebaseauthyt.util.Resource
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    val name = mutableStateOf("")
    val id = mutableStateOf("")

    val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()

    val _googleState = mutableStateOf(GoogleSignInState())
    val googleState: State<GoogleSignInState> = _googleState

//    suspend fun signIn(context: Context, launcher:) {
//
//
//    }

    fun getSignInIntent(context: Context): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .build()

        val googleSingInClient = GoogleSignIn.getClient(context, gso)

        return googleSingInClient.signInIntent
    }

    fun googleSignIn(credential: AuthCredential, context: Context, onSignInSuccess: () -> Unit) =
        viewModelScope.launch {
            authRepository.googleSignIn(credential).collect { result ->
                when (result) {
                    is Resource.Success<*> -> {
                        _googleState.value = GoogleSignInState(success = result.data)
                        onSignInSuccess()

                        updatePhotoUser(getGUser(context)?.photoUrl.toString() ?: "")
                    }

                    is Resource.Loading<*> -> {
                        _googleState.value = GoogleSignInState(loading = true)
                    }

                    is Resource.Error<*> -> {
                        _googleState.value = GoogleSignInState(error = result.message!!)
                    }
                }
            }
        }

    private fun updatePhotoUser(imageUrl: String) {
        viewModelScope.launch {
            var user = userRepository.getUserStream(1).first()
            user = user?.copy(imageUrl = imageUrl)

            user?.let {
                userRepository.updateUser(user)
            }
        }
    }

    fun getGUser(context: Context): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(context)
    }

}