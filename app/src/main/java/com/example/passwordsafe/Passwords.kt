package com.example.passwordsafe

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Passwords(

    @PrimaryKey(autoGenerate = true)
    val id : Int,

    @ColumnInfo
    val userNameUserData : String,

    @ColumnInfo
    var purpose : String,

    @ColumnInfo
    var email : String,

    @ColumnInfo
    var userName : String,

    @ColumnInfo
    var password : String
)
