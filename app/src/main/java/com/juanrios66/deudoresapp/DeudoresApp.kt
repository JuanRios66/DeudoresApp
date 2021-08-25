package com.juanrios66.deudoresapp

import android.app.Application
import androidx.room.Room
import com.juanrios66.deudoresapp.data.DeudoresAppDatabase

class DeudoresApp : Application() {

    companion object {
        lateinit var database: DeudoresAppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            DeudoresAppDatabase::class.java,
            "datos_db"
        ).allowMainThreadQueries()
            .build()
    }
}
