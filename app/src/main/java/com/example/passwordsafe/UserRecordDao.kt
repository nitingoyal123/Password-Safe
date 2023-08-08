package com.example.passwordsafe

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserRecordDao {

    @Insert
    fun insertUserRecord(userRecord: UserRecord)

//    @Update
//    fun update(oldPassword : String,userRecord: UserRecord)

    @Query("Select userName from UserRecord")
    fun getAllUserNames() : List<String>

    @Query("Select password from UserRecord where userName = :userName")
    fun getPassword(userName : String) : String

    @Query("delete from UserRecord")
    fun deleteAll()


}
