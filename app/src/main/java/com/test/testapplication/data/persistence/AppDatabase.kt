package com.test.testapplication.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.testapplication.data.model.User

@Database(entities = [User::class], version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun posterDao(): UserDao
}
