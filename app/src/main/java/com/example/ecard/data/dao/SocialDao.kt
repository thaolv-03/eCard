package com.example.ecard.data.dao

import androidx.room.*
import com.example.ecard.data.model.Social
import com.example.ecard.data.model.User
import com.example.ecard.data.model.UserWithSocialList
import kotlinx.coroutines.flow.Flow

@Dao
interface SocialDao {
    @Query("SELECT * from social")
    fun getAllSocial(): Flow<List<Social>>

    @Query("SELECT * from social WHERE socialId = :id")
    fun getSocial(id: Int): Flow<Social>

//    @Transaction
//    @Query("SELECT * FROM Social")
//    fun getSocialWithSocialList(): Flow<List<SocialWithSocialList>>

    @Query("DELETE FROM social WHERE userId = :userId")
    suspend fun deleteByUserId(userId: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(social: Social)

    @Update
    suspend fun update(social: Social)

    @Delete
    suspend fun delete(social: Social)
}