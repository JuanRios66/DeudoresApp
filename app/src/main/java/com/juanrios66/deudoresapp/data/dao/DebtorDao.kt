package com.juanrios66.deudoresapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.juanrios66.deudoresapp.data.entities.Debtor

@Dao
interface DebtorDao {
    @Insert
    fun createDebtor(debtor: Debtor)

    @Query("SELECT*FROM table_debtor")
    fun getDebtors(): MutableList<Debtor>

}