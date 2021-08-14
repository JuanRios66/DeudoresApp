package com.juanrios66.deudoresapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juanrios66.deudoresapp.data.dao.DebtorDao
import com.juanrios66.deudoresapp.data.dao.UserDao
import com.juanrios66.deudoresapp.data.entities.Debtor
import com.juanrios66.deudoresapp.data.entities.User

@Database(entities = [Debtor::class, User::class], version= 1)

abstract class DebtorDatabase: RoomDatabase() {

    abstract fun DebtorDao(): DebtorDao
    abstract fun UserDao(): UserDao

}