package com.example.ecard.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ecard.data.dao.SocialDao
import com.example.ecard.data.dao.UserDao
import com.example.ecard.data.model.Social
import com.example.ecard.data.model.User

@Database(entities = [User::class, Social::class], version = 3, exportSchema = false)
abstract class ECardDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun socialDao(): SocialDao

    companion object {
        @Volatile
        private var Instance: ECardDatabase? = null

        fun getDatabase(context: Context): ECardDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ECardDatabase::class.java, "ecard_database")
                    /**
                     * Setting this option in your app's database builder means that Room
                     * permanently deletes all data from the tables in your database when it
                     * attempts to perform a migration with no defined migration path.
                     */
                    .createFromAsset("databases/ecard_database.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}