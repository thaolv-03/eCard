package com.example.ecard.data.repository

import android.content.ClipData
import androidx.room.Query
import androidx.room.Transaction
import com.example.ecard.data.model.User
import com.example.ecard.data.model.UserWithSocialList
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUserStream(): Flow<List<User>>

    fun getUserStream(id: Int): Flow<User?>

    fun getUserWithSocialList(): Flow<List<UserWithSocialList>>

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun updateUser(user: User)

}