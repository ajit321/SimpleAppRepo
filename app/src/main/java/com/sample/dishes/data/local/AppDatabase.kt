package com.sample.dishes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sample.dishes.data.local.dao.HomeDao
import com.sample.dishes.data.local.entity.HomeModelEntity


@Database(
    entities = [HomeModelEntity::class],
    version = DatabaseMigrations.DB_VERSION
)
@TypeConverters(GenreConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getSimpleDao(): HomeDao

    companion object {
        private const val DB_NAME = "simple_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).addMigrations(*DatabaseMigrations.MIGRATIONS).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
