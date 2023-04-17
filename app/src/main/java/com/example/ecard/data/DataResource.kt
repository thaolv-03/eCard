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
            Social(SocialName.INSTAGRAM, "Le Van Thao", "https://www.facebook.com/thaolv.03"),
            Social(SocialName.LINKEDIN, "", ""),
            Social(SocialName.TIKTOK, "", ""),
            Social(SocialName.YOUTUBE, "", ""),
            Social(SocialName.TWITTER, "", ""),
        )

    )
}