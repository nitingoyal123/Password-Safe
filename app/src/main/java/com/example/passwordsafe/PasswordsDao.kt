package com.example.passwordsafe

import android.security.keystore.UserNotAuthenticatedException
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PasswordsDao {


    @Insert
    fun addPasswordData(password : Passwords)

    @Delete
    fun deletePasswordData(password : Passwords)

    @Query("select * from Passwords where userNameUserData = :userNameUserRecord")
    fun selectAllPasswords(userNameUserRecord : String) : List<Passwords>

    @Query("select purpose from Passwords where userNameUserData = :userNameUserRecord")
    fun selectAllPurposes(userNameUserRecord : String) : List<String>

    @Query("select * from Passwords where id = :id")
    fun selectSingle(id : Int) : Passwords

    @Query("delete from Passwords")
    fun deleteAll()

    //UPDATE IS PENDING\
    @Query("update Passwords set email = :email,password = :password,userName = :username where id = :id")
    fun updatePasswordData(id : Int,username : String,email : String,password : String)
}
