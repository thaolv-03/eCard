package com.example.ecard.data.dao

import android.content.ClipData
import androidx.room.*
import com.example.ecard.data.model.User
import com.example.ecard.data.model.UserWithSocialList
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * from user")
    fun getAllUser(): Flow<List<User>>

    @Query("SELECT * from user WHERE userId = :id")
    fun getUser(id: Int): Flow<User>

    @Transaction
    @Query("SELECT * FROM User")
    fun getUserWithSocialList(): Flow<List<UserWithSocialList>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}