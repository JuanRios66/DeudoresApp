package com.juanrios66.deudoresapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juanrios66.deudoresapp.data.dao.UserDAO
import com.juanrios66.deudoresapp.data.entities.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
}