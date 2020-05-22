package com.example.referenciapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.lang.ref.Reference

@Database(entities = arrayOf(PrintExercises::class, DigitalExercises::class), version = 1, exportSchema = false)
public abstract class ReferenceDatabase : RoomDatabase() {

    abstract fun referenceDao(): ReferenceDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ReferenceDatabase? = null

        fun getDatabase(context: Context): ReferenceDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReferenceDatabase::class.java,
                    "word_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}