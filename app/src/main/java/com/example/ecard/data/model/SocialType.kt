package com.example.ecard.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "social_type")
data class SocialType(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String?
)