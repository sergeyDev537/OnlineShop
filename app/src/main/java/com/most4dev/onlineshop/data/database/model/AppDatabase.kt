package com.most4dev.onlineshop.data.database.model

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.most4dev.onlineshop.data.database.dao.AccountDao

@Database(entities = [AccountDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDao

    companion object {
        const val DB_NAME = "online_shop.db"
    }

}