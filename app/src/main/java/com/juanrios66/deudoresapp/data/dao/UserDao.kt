package com.juanrios66.deudoresapp.data.dao

import androidx.room.*
import com.juanrios66.deudoresapp.data.entities.User

@Dao
interface UserDAO {
    @Insert
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT*FROM tabla_usuario where email LIKE:email")
    fun searchUser(email: String): User

    @Update
    fun updateUser(user: User)
}