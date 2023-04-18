package com.example.ecard.data

import androidx.compose.ui.graphics.painter.Painter
import com.example.ecard.data.model.Social
import com.example.ecard.data.model.SocialName
import com.example.ecard.data.model.User

object dataResource {
    val userExample = User(
        name = "Lê Văn Thảo",
        phone = "0947180074",
        email = "thaolv.21it@vku.udn.vn",
        birthday = "06/08/2003",
        socialList = listOf(
            Social(SocialName.FACEBOOK, "Le Van Thao", "https://www.facebook.com/thaolv.03"),
            Social(SocialName.INSTAGRAM, "Le Van Thao", "https://www.instagram.com/_thaolv_"),
            Social(SocialName.TIKTOK, "thaolv.03", "https://www.tiktok.com/@thaolv.03"),
            Social(SocialName.YOUTUBE, "Thao Le Van", "https://www.youtube.com/@thaolevan2760"),
            Social(SocialName.LINKEDIN, "Thao Le Van", "https://www.linkedin.com/in/thaolv03/"),
            Social(SocialName.TWITTER, "Thao Le Van", "https://twitter.com/thaolv03"),
        )
    )
}