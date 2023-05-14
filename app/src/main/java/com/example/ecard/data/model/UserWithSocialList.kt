package com.example.ecard.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithSocialList(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val socialList: List<Social>
)