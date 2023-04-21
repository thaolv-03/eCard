package com.example.ecard.data

import android.content.Context
import com.example.ecard.data.database.ECardDatabase
import com.example.ecard.data.repository.OfflineSocialRepository
import com.example.ecard.data.repository.OfflineUserRepository
import com.example.ecard.data.repository.SocialRepository
import com.example.ecard.data.repository.UserRepository

interface AppContainer {
    val userRepository: UserRepository
    val socialRepository: SocialRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {

    override val userRepository: UserRepository by lazy {
        OfflineUserRepository(ECardDatabase.getDatabase(context).userDao())
    }

    override val socialRepository: SocialRepository by lazy {
        OfflineSocialRepository(ECardDatabase.getDatabase(context).socialDao())
    }


}