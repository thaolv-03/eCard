package com.example.ecard.data.repository

import com.example.ecard.data.dao.SocialDao
import com.example.ecard.data.model.Social
import kotlinx.coroutines.flow.Flow

class OfflineSocialRepository(private val socialDao: SocialDao) : SocialRepository {
    override fun getAllSocialStream(): Flow<List<Social>> = socialDao.getAllSocial()

    override fun getSocialStream(id: Int): Flow<Social?> = socialDao.getSocial(id)

//    fun getSocialWithSocialList(): Flow<List<SocialWithSocialList>>

    override suspend fun insertSocial(social: Social) = socialDao.insert(social)

    override suspend fun deleteSocial(social: Social) = socialDao.delete(social)

    override suspend fun updateSocial(social: Social) = socialDao.update(social)
}