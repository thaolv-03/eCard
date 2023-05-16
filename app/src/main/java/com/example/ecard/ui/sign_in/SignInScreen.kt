package com.example.ecard.ui.sign_in

import android.util.Log
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ecard.R
import com.example.ecard.navigation.NavigationDestination
import com.example.ecard.ui.home.HomeDestination
import com.example.ecard.ui.home.TopAppBarEdit
import com.example.firebaseauthyt.data.AuthRepositoryImpl
import com.example.firebaseauthyt.util.Resource
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


object SignInDestination : NavigationDestination {
    override val route = "sign_in"
    override val titleRes = R.string.app_name
}

@Composable
fun SignInScreen(
    navigateTo: (String) -> Unit,
    viewModel: SignInViewModel = SignInViewModel(AuthRepositoryImpl(FirebaseAuth.getInstance()))
) {

    remember {
        if ((Firebase.auth.currentUser != null) or (viewModel.googleState.value.success != null)) {
            navigateTo(HomeDestination.route)
            Log.e("TEST", "LOOP?")
        }
    }


    val context = LocalContext.current

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                viewModel.googleSignIn(credentials) { navigateTo(HomeDestination.route) }
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
                    launcher.launch(viewModel.getSignInIntent(context))
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