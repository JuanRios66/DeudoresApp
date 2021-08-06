package com.juanrios66.deudoresapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_debtor")
data class Debtor (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") val id: Int,
    @ColumnInfo(name="name")val name: String,
    @ColumnInfo(name="phone")val phone: String,
    @ColumnInfo(name="amount")val amount: Long

)