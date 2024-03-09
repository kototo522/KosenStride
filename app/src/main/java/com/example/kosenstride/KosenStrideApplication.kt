package com.example.kosenstride

import android.app.Application
import androidx.room.Room
import com.example.kosenstride.data.local.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KosenStrideApplication : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database =
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "app_database",
            ).build()
    }
}
