package com.example.ecard

import android.app.Application
import com.example.ecard.data.AppContainer
import com.example.ecard.data.AppDataContainer

class ECardApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}