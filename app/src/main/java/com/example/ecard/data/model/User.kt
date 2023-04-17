package com.example.ecard.data.model

import androidx.compose.ui.graphics.painter.Painter

data class User(
    val name: String,
    val phone: String,
    val email: String,
    val birthday: String,
    val image: Painter? = null,
    val socialList: List<Social> = listOf<Social>(
        Social(SocialName.FACEBOOK, "", ""),
        Social(SocialName.INSTAGRAM, "", ""),
        Social(SocialName.LINKEDIN, "", ""),
        Social(SocialName.TIKTOK, "", ""),
        Social(SocialName.YOUTUBE, "", ""),
        Social(SocialName.TWITTER, "", ""),
    )
)

data class Social(
    val socialName: SocialName,
    val userName: String,
    val link: String
)

enum class SocialName(val sName: String) {
    FACEBOOK("Facebook"),
    INSTAGRAM("Instagram"),
    LINKEDIN("LinkedIn"),
    TIKTOK("Tiktok"),
    YOUTUBE("Youtube"),
    TWITTER("Twitter")
}

data class SocialList(
    val facebook: String,
    val instagram: String,
    val linkedIn: String,
    val tiktok: String,
    val youtube: String,
    val twitter: String
)