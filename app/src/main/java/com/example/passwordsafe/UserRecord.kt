package com.example.passwordsafe

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserRecord(
    @PrimaryKey
    var userName : String,
    @ColumnInfo
    var password : String
)
