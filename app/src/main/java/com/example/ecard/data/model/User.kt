package com.example.ecard.data.model

import androidx.compose.ui.graphics.painter.Painter
import androidx.room.*

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    var userId: Int? = 0,
    var name: String? = null,
    var phone: String? = null,
    var email: String? = null,
    var birthday: String? = null,
    @Ignore var image: Painter? = null,
    var isMe: Boolean? = null
) {
    constructor() : this(0) {}
}

enum class SocialName(val sTypeId: Int, val sName: String) {
    FACEBOOK(0, "Facebook"),
    INSTAGRAM(1, "Instagram"),
    TIKTOK(2, "Tiktok"),
    YOUTUBE(3, "Youtube"),
    LINKEDIN(4, "LinkedIn"),
    TWITTER(5, "Twitter")
}

object CONST {
    val SOCIAL_TYPE = mapOf<Any, Any>(
        0 to SocialName.FACEBOOK.sName,
        1 to SocialName.INSTAGRAM.sName,
        2 to SocialName.TIKTOK.sName,
        3 to SocialName.YOUTUBE.sName,
        4 to SocialName.LINKEDIN.sName,
        5 to SocialName.TWITTER.sName,

        SocialName.FACEBOOK.sName to 0,
        SocialName.INSTAGRAM.sName to 1,
        SocialName.TIKTOK.sName to 2,
        SocialName.YOUTUBE.sName to 3,
        SocialName.LINKEDIN.sName to 4,
        SocialName.TWITTER.sName to 5,
    )
}