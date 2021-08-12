package com.juanrios66.deudoresapp

import android.app.Application
import androidx.room.Room
import com.juanrios66.deudoresapp.data.DebtorDatabase
import com.juanrios66.deudoresapp.data.UserDatabase

class DeudoresApp: Application() {

    companion object{
        lateinit var database: DebtorDatabase
        lateinit var database2: UserDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            DebtorDatabase::class.java,
            "debtor_db"
        ).allowMainThreadQueries()
            .build()

        database2 = Room.databaseBuilder(
            this,
            UserDatabase::class.java,
            "user_db"
        ).allowMainThreadQueries()
            .build()
    }

}