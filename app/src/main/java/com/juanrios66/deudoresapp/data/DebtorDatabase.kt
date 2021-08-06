package com.juanrios66.deudoresapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juanrios66.deudoresapp.data.dao.DebtorDao
import com.juanrios66.deudoresapp.data.entities.Debtor

@Database(entities = [Debtor::class], version= 1)

abstract class DebtorDatabase: RoomDatabase() {

    abstract fun DebtorDao(): DebtorDao
}