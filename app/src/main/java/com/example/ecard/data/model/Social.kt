package com.example.ecard.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "social")
data class Social(
    @PrimaryKey(autoGenerate = true)
    var socialId: Int = 0,
    @ColumnInfo(name = "social_Type_id")
    var socialTypeId: Int,
    @ColumnInfo(name = "user_name")
    var userName: String,
    var link: String,
    var userId: Int = 0,
)