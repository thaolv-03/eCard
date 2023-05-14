package com.example.ecard.data

import androidx.compose.ui.graphics.painter.Painter
import com.example.ecard.data.model.Social
import com.example.ecard.data.model.SocialName
import com.example.ecard.data.model.User
import com.example.ecard.data.model.UserWithSocialList

object dataResource {
    val userExample = UserWithSocialList(
        User(
            name = "Lê Văn Thảo",
            phone = "0947180074",
            email = "thaolv.21it@vku.udn.vn",
            birthday = "06/08/2003"
        ),
        listOf(
            Social(
                socialTypeId = 0,
                userName = "Le Van Thao",
                link = "https://www.facebook.com/thaolv.03"
            ),
            Social(
                socialTypeId = 1,
                userName = "Le Van Thao",
                link = "https://www.instagram.com/_thaolv_"
            ),
            Social(
                socialTypeId = 2,
                userName = "thaolv.03",
                link = "https://www.tiktok.com/@thaolv.03"
            ),
            Social(
                socialTypeId = 3,
                userName = "Thao Le Van",
                link = "https://www.youtube.com/@thaolevan2760"
            ),
            Social(
                socialTypeId = 4,
                userName = "Thao Le Van",
                link = "https://www.linkedin.com/in/thaolv03/"
            ),
            Social(
                socialTypeId = 5,
                userName = "Thao Le Van",
                link = "https://twitter.com/thaolv03"
            ),
        )
    )
}

