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
            Social(socialName = SocialName.FACEBOOK, userName = "Le Van Thao", link = "https://www.facebook.com/thaolv.03"),
            Social(socialName = SocialName.INSTAGRAM, userName = "Le Van Thao", link = "https://www.instagram.com/_thaolv_"),
            Social(socialName = SocialName.TIKTOK, userName = "thaolv.03", link = "https://www.tiktok.com/@thaolv.03"),
            Social(socialName = SocialName.YOUTUBE, userName = "Thao Le Van", link = "https://www.youtube.com/@thaolevan2760"),
            Social(socialName = SocialName.LINKEDIN, userName = "Thao Le Van", link = "https://www.linkedin.com/in/thaolv03/"),
            Social(socialName = SocialName.TWITTER, userName = "Thao Le Van", link = "https://twitter.com/thaolv03"),
        )
    )
}