package com.example.referenciapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.ref.Reference

@Database(entities = [PrintExercises::class, DigitalExercises::class], version = 1, exportSchema = false)
public abstract class ReferenceDatabase : RoomDatabase() {

    abstract fun referenceDao(): ReferenceDao

    private class ReferenceDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let {database ->
                scope.launch {
                    var referenceDao = database.referenceDao()

                    // Print Exercises
                    referenceDao.insertPrintAll(
                        PrintExercises(
                            id = 0,
                            authors = "Wilber, K.",
                            isEditor = true,
                            year = "(1997)",
                            title = "El paradigma holográfico",
                            city = "Barcelona",
                            country = "España",
                            publisher = "Kairós",
                            resourceType = 1,
                            exerciseType = 1,
                            description = "Libro con editor"
                        ),
                        PrintExercises(
                            id = 1,
                            authors = "Pérez, M., Cáceres, I., López, J. L., Álvarez, A., Casado, C., López Martínez, C., ... Morrison, W.",
                            year = "(2016)",
                            title = "Manual de psicoterapia",
                            city = "Buenos Aires",
                            country = "Argentina",
                            publisher = "Panamericana",
                            resourceType = 1,
                            exerciseType = 2,
                            description = "Libro con más de cuatro autores"
                        )
                    )

                    // Digital Exercises
                    referenceDao.insertDigitalAll(
                        DigitalExercises(
                            id = 0,
                            authors = "Merkh, D. y Merkh, C. S.",
                            year = "(2015)",
                            title = "101 ideas creativas para familias",
                            url = "Recuperado de http://0-site.ebrary.com.jabega.uma.es/lib/bibliotecauma/detail.action?docID=11362387",
                            resourceType = 1,
                            exerciseType = 1,
                            description = "Libro digital simple"
                        ),
                        DigitalExercises(
                            id = 1,
                            term = "Marqués",
                            year = "(s.f.)",
                            institution = "En el Diccionario de la Real Academia Española",
                            edition = "(23ra ed.)",
                            source = "Recuperado de http://dle.rae.es/?id=OT28kUW",
                            resourceType = 2,
                            exerciseType = 2,
                            description = "Diccionario digital"
                        )
                    )
                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ReferenceDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ReferenceDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReferenceDatabase::class.java,
                    "reference_database"
                )
                    .addCallback(ReferenceDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}