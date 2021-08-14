package com.juanrios66.deudoresapp

import android.app.Application
import androidx.room.Room
import com.juanrios66.deudoresapp.data.DebtorDatabase

class DeudoresApp: Application() {

    companion object{
        lateinit var database: DebtorDatabase
        //lateinit var database2: UserDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            DebtorDatabase::class.java,
            "datos_db"
        ).allowMainThreadQueries()
            .build()

    }

}