package com.example.teixeirakebab.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.teixeirakebab.data.dao.OrderDao
import com.example.teixeirakebab.data.entity.OrderEntity
import com.example.teixeirakebab.data.entity.OrderItemEntity

/**
 * Room database for the kebab store app
 */
@Database(
    entities = [OrderEntity::class, OrderItemEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun orderDao(): OrderDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "kebab_store_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
} 