package com.example.ecard.data.model

import androidx.room.*

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,
    var name: String? = null,
    var phone: String? = null,
    var email: String? = null,
    var birthday: String? = null,
    var imageUrl: String? = null,
    var isMe: Boolean? = null
)