package com.example.passwordsafe


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Passwords::class,UserRecord::class], version = 3)
abstract class MyDatabase : RoomDatabase() {

    abstract fun getUserRecordDao() : UserRecordDao
    abstract fun getPasswordsDao() : PasswordsDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        private val migration_1_2 = object : Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("drop table UserRecord")
                database.execSQL("create table UserRecord(userName TEXT NOT NULL PRIMARY KEY,password TEXT NOT NULL)")
            }

        }
        private val migration_2_3 = object : Migration(2,3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("drop table Passwords")
                database.execSQL("CREATE TABLE Passwords (" +
                        "    userNameUserData TEXT NOT NULL," +
                        "    password TEXT NOT NULL," +
                        "    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        "    userName TEXT NOT NULL," +
                        "    purpose TEXT NOT NULL," +
                        "    email TEXT NOT NULL" +
                        ")")

            }
        }
        fun getDatabase(context: Context): MyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "MyDatabase"
                ).allowMainThreadQueries()
                    .addMigrations(migration_1_2)
                    .addMigrations(migration_2_3)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}