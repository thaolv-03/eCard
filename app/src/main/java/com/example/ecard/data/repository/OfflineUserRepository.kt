package com.example.ecard.data.repository

import com.example.ecard.data.dao.UserDao
import com.example.ecard.data.model.User
import com.example.ecard.data.model.UserWithSocialList
import kotlinx.coroutines.flow.Flow

class OfflineUserRepository(private val userDao: UserDao) : UserRepository {
    override fun getAllUserStream(): Flow<List<User>> = userDao.getAllUser()

    override fun getUserStream(id: Int): Flow<User?> = userDao.getUser(id)

    override fun getUserWithSocialList(userId: Int): Flow<UserWithSocialList> = userDao.getUserWithSocialList(userId)

    override suspend fun insertUser(user: User) = userDao.insert(user)

    override suspend fun deleteUser(user: User) = userDao.delete(user)

    override suspend fun updateUser(user: User) = userDao.update(user)

}