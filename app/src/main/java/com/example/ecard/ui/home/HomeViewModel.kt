package com.example.ecard.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.example.ecard.data.dataResource.userExample
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update


class HomeViewModel : ViewModel() {
    //TO DO: get user from Repository
    private val user = userExample

    val uiState = MutableStateFlow(
        HomeUiState(
            name = user.name,
            birthday = user.birthday,
            phone = user.phone,
            email = user.email,
            image = user.image,
        )
    )

    fun onPickSocialItem(socialId: Int) {
        uiState.update {
            it.copy(currentPickSocial = user.socialList[socialId])
        }
    }

    fun onCancelOrDismissClickPopup() {
        uiState.update {
            it.copy(currentPickSocial = null)
        }
    }

    fun onClickAccess(context: Context, url: String) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            "http://$url"
        }



        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(context, browserIntent, null)
    }

}