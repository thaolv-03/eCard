package com.example.ecard.ui.sign_in

import android.app.Activity.RESULT_OK
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauthyt.data.AuthRepository
import com.example.firebaseauthyt.util.Resource
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SignInViewModel(private val authRepository: AuthRepository) : ViewModel() {
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

    fun googleSignIn(credential: AuthCredential) = viewModelScope.launch {
        authRepository.googleSignIn(credential).collect { result ->
            when (result) {
                is Resource.Success<*> -> {
                    _googleState.value = GoogleSignInState(success = result.data)
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

}