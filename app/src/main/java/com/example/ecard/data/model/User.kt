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
    @Ignore var socialList: List<Social> = listOf<Social>(
        Social(socialName = SocialName.FACEBOOK, userName = "", link = ""),
        Social(socialName = SocialName.INSTAGRAM, userName = "", link = ""),
        Social(socialName = SocialName.LINKEDIN, userName = "", link = ""),
        Social(socialName = SocialName.TIKTOK, userName = "", link = ""),
        Social(socialName = SocialName.YOUTUBE, userName = "", link = ""),
        Social(socialName = SocialName.TWITTER, userName = "", link = ""),
    )
) {
    constructor() : this(0) {}
}

@Entity(tableName = "social")
data class Social(
    @PrimaryKey(autoGenerate = true)
    var socialId: Int = 0,
    @ColumnInfo(name = "social_name")
    var socialName: SocialName,
    @ColumnInfo(name = "user_name")
    var userName: String,
    var link: String,
    var userId: Int = 0,
)

data class UserWithSocialList(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "socialId"
    )
    val socialList: List<Social>
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