package com.juanrios66.deudoresapp.data.dao

import androidx.room.*
import com.juanrios66.deudoresapp.data.entities.User

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT*FROM table_user where email LIKE:email")
    fun searchUser(email: String): User

    @Update
    fun updateUser(user: User)
}
