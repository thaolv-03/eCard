package com.example.ecard.ui.sign_in

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ecard.R
import com.example.ecard.navigation.NavigationDestination
import com.example.ecard.ui.home.TopAppBarEdit
import com.example.firebaseauthyt.data.AuthRepositoryImpl
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


object SignInDestination : NavigationDestination {
    override val route = "sign_in"
    override val titleRes = R.string.app_name
}

@Composable
fun SignInScreen(viewModel: SignInViewModel = SignInViewModel(AuthRepositoryImpl(FirebaseAuth.getInstance()))) {
    val context = LocalContext.current

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                viewModel.googleSignIn(credentials)
            } catch (it: ApiException) {
                print(it)
            }
        }


    Scaffold(
        topBar = {
            TopAppBarEdit(title = R.string.app_name)
        },

        ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_app_2),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 100.dp)
                    .size(260.dp)
            )

            Spacer(modifier = Modifier.height(200.dp))

            Text(
                text = "Đăng nhập với",
                style = MaterialTheme.typography.h5
            )
            Button(
                onClick = {
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .requestIdToken("738243026577-8rlo6ava0nbae9m6eka88l641pdr0arj.apps.googleusercontent.com")
                        .build()

                    val googleSingInClient = GoogleSignIn.getClient(context, gso)

                    launcher.launch(googleSingInClient.signInIntent)
                },
                modifier = Modifier.padding(top = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    Color.White
                ),
                shape = CircleShape
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
        Spacer(modifier = Modifier.padding(it))
    }
}