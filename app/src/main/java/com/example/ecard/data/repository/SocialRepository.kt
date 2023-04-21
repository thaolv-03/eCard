package com.example.ecard.data.repository

import com.example.ecard.data.model.Social
import com.example.ecard.data.model.User
import com.example.ecard.data.model.UserWithSocialList
import kotlinx.coroutines.flow.Flow

interface SocialRepository {
    fun getAllSocialStream(): Flow<List<Social>>

    fun getSocialStream(id: Int): Flow<Social?>

//    fun getSocialWithSocialList(): Flow<List<SocialWithSocialList>>

    suspend fun insertSocial(social: Social)

    suspend fun deleteSocial(social: Social)

    suspend fun updateSocial(social: Social)
}