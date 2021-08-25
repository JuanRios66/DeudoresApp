package com.juanrios66.deudoresapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juanrios66.deudoresapp.data.local.dao.DebtorDao
import com.juanrios66.deudoresapp.data.local.dao.UserDao
import com.juanrios66.deudoresapp.data.local.entities.Debtor
import com.juanrios66.deudoresapp.data.local.entities.User

@Database(entities = [Debtor::class, User::class], version = 1)
abstract class DeudoresAppDatabase : RoomDatabase() {
    abstract fun DebtorDao(): DebtorDao
    abstract fun UserDao(): UserDao
}
