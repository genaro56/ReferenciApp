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
                            year = "(1997).",
                            title = "El paradigma holográfico.",
                            city = "Barcelona,",
                            country = "España:",
                            publisher = "Kairós.",
                            resourceType = 1,
                            description = "Libro con editor"
                        ),
                        PrintExercises(
                            id = 1,
                            authors = "Pérez, M., Cáceres, I., López, J. L., Álvarez, A., Casado, C., López Martínez, C., ... Morrison, W.",
                            year = "(2016).",
                            title = "Manual de psicoterapia.",
                            city = "Buenos Aires,",
                            country = "Argentina:",
                            publisher = "Panamericana",
                            resourceType = 1,
                            exerciseType = 2,
                            description = "Libro con más de cuatro autores"
                        ),
                        PrintExercises(
                            id = 2,
                            authors = "García Allende, J.",
                            isEditor = true,
                            year = "(2014).",
                            title = "Y fui toda en mí: Antología poética en el centenario del natalicio de Julia de Burgos.",
                            city = "Cataño,",
                            country = "Puerto Rico:",
                            publisher = "SM.",
                            resourceType = 1,
                            description = "Antología"
                        ),
                        PrintExercises(
                            id = 3,
                            authors = "Quintana Cabanas, J. M.",
                            isEditor = true,
                            year = "(2010).",
                            title = "Educación Social. Antología de Textos Clásicos.",
                            city = "Madrid,",
                            country = "España:",
                            publisher = "Narcea",
                            resourceType = 1,
                            exerciseType = 2,
                            description = "Segunda Antología"
                        ),
                        PrintExercises(
                            id = 4,
                            authors = "Mainer Baqué, J. C.",
                            year = "(1986).",
                            chapterTitle = "El cuento semanal (1907-1912): texto y contexto.",
                            editors = "En A. Egido y Y. R. Fonquerne (eds.)," ,
                            title = "Formas breves del relato",
                            pages = "(pp. 207-220).",
                            city = "Zaragoza:",
                            publisher = "Universidad de Zaragoza.",
                            resourceType = 2,
                            description = "Capítulo de libro"
                        ),
                        PrintExercises(
                            id = 5,
                            authors = "Tychoson, D. A.",
                            year = "(2011).",
                            chapterTitle = "History and varieties of reference services.",
                            editors = "En R. E. Bopp y R. Smith (Eds).",
                            title = "Reference and information services",
                            pages = "(pp. 3-28)",
                            city = "Santa Bárbara,",
                            country = "EE.UU.:",
                            publisher = "Libraries Unlimited",
                            resourceType = 2,
                            exerciseType = 2,
                            description = "Capítulo de libro"
                        ),
                        PrintExercises(
                            id = 6,
                            authors = "Real Academia Española.",
                            year = "(2011).",
                            term = "Disquisición.",
                            source = "En Diccionario de la lengua española",
                            edition = "(22.a ed.).",
                            city = "Madrid,",
                            country = "España.",
                            resourceType = 3,
                            description = "Entrada de diccionario"
                        ),
                        PrintExercises(
                            id = 7,
                            authors = "Merriam-Webster",
                            year = "(2005).",
                            term = "Chardonnay.",
                            source = "En Merriam-Webster Dictionary",
                            edition = "(11.a ed).",
                            city = "Springfield,",
                            country = "EEUU:",
                            publisher = "Merriam-Webster",
                            resourceType = 3,
                            exerciseType = 2,
                            description = "Entrada de diccionario con campo de editorial"
                        ),
                        PrintExercises(
                            id = 8,
                            authors = "Bergmann, P. G.",
                            year = "(1993)",
                            term = "Relativity.",
                            source = "En New Encyclopedia Britannica",
                            volumeAndPages = "(Vol. 26, pp. 501-508).",
                            city = "Chicago,",
                            country = "EE.UU.:",
                            publisher = "Encyclopedia Britannica",
                            resourceType = 4,
                            description = "Entrada de enciclopedia"
                        ),
                        PrintExercises(
                            id = 9,
                            authors = "MC Graw-Hill.",
                            year = "(1999-2000).",
                            term = "Revolución Francesa.",
                            source = "En Nueva Enciclopedia Mc Graw Hill.",
                            volumeAndPages = "(Vol. 10, pp. 501-508).",
                            city = "Nueva York,",
                            country = "EE.UU.:",
                            publisher = "Mc Graw Hill",
                            resourceType = 4,
                            exerciseType = 2,
                            description = "Entrada de enciclopedia"
                        ),
                        PrintExercises(
                            id = 10,
                            authors = "Ramírez Prado, J.",
                            date = "(15 de agosto de 2014).",
                            title = "Con inversión de US$350 millones, Grupo PIO iniciará construcción de un puerto en Antioquía.",
                            newspaper = "La República,",
                            pages = "p. 21.",
                            resourceType = 5,
                            description = "Artículo de periódico"
                        ),
                        PrintExercises(
                            id = 11,
                            authors = "Pita, E. V.",
                            date = "(5 de noviembre de 2012).",
                            title = "Cae la banda gallega que falsificaba el osito de Tous.",
                            newspaper = "La Voz de Galicia,",
                            pages = "p. 7.",
                            resourceType = 5,
                            exerciseType = 2,
                            description = "Artículo de periódico"
                        ),
                        PrintExercises(
                            id = 12,
                            authors = "Pérez de la Dehesa, R.",
                            year = "(1969).",
                            title = "La Editorial Sempere en Hispanoamérica y Expaña.",
                            journal = "Revisa Iberoamericana,",
                            volume = "XXXV",
                            issue = "(69),",
                            pages = "pp. 551-555.",
                            resourceType = 6,
                            description = "Artículo de revista"
                        ),
                        PrintExercises(
                            id = 13,
                            authors = "Sánchez Granjel, L.",
                            year = "(1968)",
                            title = "La novela corta en España",
                            journal = "Cuadernos Hispanoamericanos,",
                            issue = "(222),",
                            pages = "pp. 447-508.",
                            resourceType = 6,
                            exerciseType = 2,
                            description = "Artículo de revista"
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