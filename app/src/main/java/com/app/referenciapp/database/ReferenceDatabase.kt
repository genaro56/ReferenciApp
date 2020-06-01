package com.app.referenciapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [PrintExercises::class, DigitalExercises::class], version = 2, exportSchema = false)
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
                            authors = "Wilber, K. ",
                            isEditor = true,
                            year = "(1997). ",
                            title = "El paradigma holográfico. ",
                            city = "Barcelona, ",
                            country = "España: ",
                            publisher = "Kairós.",
                            resourceType = 1,
                            description = "Libro con editor"
                        ),
                        PrintExercises(
                            id = 1,
                            authors = "Pérez, M., Cáceres, I., López, J. L., Álvarez, A., Casado, C., López Martínez, C., ... Morrison, W. ",
                            year = "(2016). ",
                            title = "Manual de psicoterapia. ",
                            city = "Buenos Aires, ",
                            country = "Argentina: ",
                            publisher = "Panamericana ",
                            resourceType = 1,
                            exerciseType = 2,
                            description = "Libro con más de cuatro autores"
                        ),
                        PrintExercises(
                            id = 2,
                            authors = "García Allende, J. ",
                            isEditor = true,
                            year = "(2014). ",
                            title = "Y fui toda en mí: Antología poética en el centenario del natalicio de Julia de Burgos. ",
                            city = "Cataño, ",
                            country = "Puerto Rico: ",
                            publisher = "SM. ",
                            resourceType = 1,
                            description = "Antología"
                        ),
                        PrintExercises(
                            id = 3,
                            authors = "Quintana Cabanas, J. M. ",
                            isEditor = true,
                            year = "(2010). ",
                            title = "Educación Social. Antología de Textos Clásicos. ",
                            city = "Madrid, ",
                            country = "España: ",
                            publisher = "Narcea ",
                            resourceType = 1,
                            exerciseType = 2,
                            description = "Segunda Antología"
                        ),
                        PrintExercises(
                            id = 4,
                            authors = "Mainer Baqué, J. C. ",
                            year = "(1986). ",
                            chapterTitle = "El cuento semanal (1907-1912): texto y contexto. ",
                            editors = "En A. Egido y Y. R. Fonquerne (eds.), ",
                            title = "Formas breves del relato ",
                            pages = "(pp. 207-220). ",
                            city = "Zaragoza: ",
                            publisher = "Universidad de Zaragoza.",
                            resourceType = 2,
                            description = "Capítulo de libro"
                        ),
                        PrintExercises(
                            id = 5,
                            authors = "Tychoson, D. A. ",
                            year = "(2011). ",
                            chapterTitle = "History and varieties of reference services. ",
                            editors = "En R. E. Bopp y R. Smith (Eds). ",
                            title = "Reference and information services ",
                            pages = "(pp. 3-28) ",
                            city = "Santa Bárbara, ",
                            country = "EE.UU.: ",
                            publisher = "Libraries Unlimited",
                            resourceType = 2,
                            exerciseType = 2,
                            description = "Capítulo de libro"
                        ),
                        PrintExercises(
                            id = 6,
                            authors = "Real Academia Española. ",
                            year = "(2011). ",
                            term = "Disquisición. ",
                            source = "En Diccionario de la lengua española ",
                            edition = "(22.a ed.). ",
                            city = "Madrid, ",
                            country = "España. ",
                            resourceType = 3,
                            description = "Entrada de diccionario"
                        ),
                        PrintExercises(
                            id = 7,
                            authors = "Merriam-Webster ",
                            year = "(2005). ",
                            term = "Chardonnay. ",
                            source = "En Merriam-Webster Dictionary ",
                            edition = "(11.a ed). ",
                            city = "Springfield, ",
                            country = "EEUU: ",
                            publisher = "Merriam-Webster ",
                            resourceType = 3,
                            exerciseType = 2,
                            description = "Entrada de diccionario con campo de editorial"
                        ),
                        PrintExercises(
                            id = 8,
                            authors = "Bergmann, P. G. ",
                            year = "(1993) ",
                            term = "Relativity. ",
                            source = "En New Encyclopedia Britannica ",
                            volumeAndPages = "(Vol. 26, pp. 501-508). ",
                            city = "Chicago, ",
                            country = "EE.UU.: ",
                            publisher = "Encyclopedia Britannica ",
                            resourceType = 4,
                            description = "Entrada de enciclopedia"
                        ),
                        PrintExercises(
                            id = 9,
                            authors = "MC Graw-Hill. ",
                            year = "(1999-2000). ",
                            term = "Revolución Francesa. ",
                            source = "En Nueva Enciclopedia Mc Graw Hill. ",
                            volumeAndPages = "(Vol. 10, pp. 501-508). ",
                            city = "Nueva York, ",
                            country = "EE.UU.: ",
                            publisher = "Mc Graw Hill ",
                            resourceType = 4,
                            exerciseType = 2,
                            description = "Entrada de enciclopedia"
                        ),
                        PrintExercises(
                            id = 10,
                            authors = "Ramírez Prado, J. ",
                            date = "(15 de agosto de 2014). ",
                            title = "Con inversión de US$350 millones, Grupo PIO iniciará construcción de un puerto en Antioquía. ",
                            newspaper = "La República, ",
                            pages = "p. 21. ",
                            resourceType = 5,
                            description = "Artículo de periódico"
                        ),
                        PrintExercises(
                            id = 11,
                            authors = "Pita, E. V. ",
                            date = "(5 de noviembre de 2012). ",
                            title = "Cae la banda gallega que falsificaba el osito de Tous. ",
                            newspaper = "La Voz de Galicia, ",
                            pages = "p. 7. ",
                            resourceType = 5,
                            exerciseType = 2,
                            description = "Artículo de periódico"
                        ),
                        PrintExercises(
                            id = 12,
                            authors = "Pérez de la Dehesa, R. ",
                            year = "(1969). ",
                            title = "La Editorial Sempere en Hispanoamérica y Expaña. ",
                            journal = "Revisa Iberoamericana, ",
                            volume = "XXXV ",
                            issue = "(69), ",
                            pages = "pp. 551-555.",
                            resourceType = 6,
                            description = "Artículo de revista"
                        ),
                        PrintExercises(
                            id = 13,
                            authors = "Sánchez Granjel, L. ",
                            year = "(1968) ",
                            title = "La novela corta en España ",
                            journal = "Cuadernos Hispanoamericanos, ",
                            issue = "(222), ",
                            pages = "pp. 447-508. ",
                            resourceType = 6,
                            exerciseType = 2,
                            description = "Artículo de revista"
                        )
                    )

                    // Digital Exercises
                    referenceDao.insertDigitalAll(
                        DigitalExercises(
                            id = 0,
                            authors = "Merkh, D. y Merkh, C. S. ",
                            year = "(2015). ",
                            title = "101 ideas creativas para familias. ",
                            url = "Recuperado de http://0-site.ebrary.com.jabega.uma.es/lib/bibliotecauma/detail.action?docID=11362387 ",
                            resourceType = 1,
                            description = "Libro digital simple"
                        ),
                        DigitalExercises(
                            id = 1,
                            authors = "Barriocanal López, Y. ",
                            year = "(2016). ",
                            title = "La actividad escultórica en Ourense, del Renacimiento al Barroco ",
                            url = "Recuperado de http://0-site.ebrary.com.jabega.uma.es/lib/bibliotecauma/detail.action?docID=11361917",
                            resourceType = 1,
                            description = "Libro digital simple"
                        ),
                        DigitalExercises(
                            id = 2,
                            term = "Marqués. ",
                            year = "(s.f.). ",
                            institution = "En el Diccionario de la Real Academia Española  ",
                            edition = "(23ra ed.). ",
                            url = "Recuperado de http://dle.rae.es/?id=OT28kUW ",
                            resourceType = 2,
                            exerciseType = 2,
                            description = "Diccionario digital"
                        ),
                        DigitalExercises(
                            id = 3,
                            term = "Thesis. ",
                            year = "(s.f.). ",
                            institution = "En Merriam-Webster's online dictionary ",
                            edition = "(11va. ed). ",
                            url = "Recuperado de http://www.merriam-webster.com/dictionary/thesis",
                            resourceType = 2,
                            exerciseType = 2,
                            description = "Diccionario digital"
                        ),
                        DigitalExercises(
                            id = 4,
                            authors = "Encyclopedia Britannica Inc., ",
                            year = "(1999-2000). ",
                            term = "Wildlife Conservation. ",
                            source = "Encyclopedia Britannica [versión electrónica]. ",
                            city = "New York, ",
                            country = "EU: ",
                            publisher = "Encyclopedia Britannica Inc., ",
                            url = "Recuperado de http://britannica.com",
                            resourceType = 3,
                            exerciseType = 2,
                            description = "Enciclopedia digital con autor institucional"
                            ),
                        DigitalExercises(
                            id = 5,
                            authors = "Graham, G. ",
                            year = "(2005). ",
                            term = "Behaviorism. ",
                            hasEditor = true,
                            editor = "En E. N. Zalta ",
                            source = "The Stanford Encyclopedia of Philosophy ",
                            edition = "(Fall 2007 ed.). ",
                            url = "Recuperado de http://plato.stanford.edu/entries/behaviorism",
                            resourceType = 3,
                            exerciseType = 2,
                            description = "Enciclopedia digital con editor"
                            ),
                        DigitalExercises(
                            id = 6,
                            corp = "Oficina Internacional del Trabajo. ",
                            year = "(2002). ",
                            title = "Directrices relativas a los sistemas de gestión de la seguridad y la salud en el trabajo ILO-OSH 2001. ",
                            city = "Ginebra, ",
                            country = "Suiza: ",
                            publisher = "Oficina Internacional del Trabajo",
                            resourceType = 4,
                            exerciseType = 2,
                            description = "Página con autor corporativo"
                            ),
                        DigitalExercises(
                            id = 7,
                            corp = "American Psychological Association. ",
                            year = "(2010). ",
                            title = "Publication Manual of the American Psychological Association. ",
                            edition = "(6ta ed.). ",
                            city = "Washington D.C., ",
                            country = "EE.UU.: ",
                            publisher = "American Psychological Association",
                            resourceType = 4,
                            description = "Página con autor corporativo"
                            ),
                        DigitalExercises(
                            id = 8,
                            authors = "Holmes, T. H. y Rahe, R. H. ",
                            year = "(1968). ",
                            title = "The social readjustment rating scale. ",
                            journal = "Journal of Psychosomatic Research, ",
                            issue = "11, ",
                            pages = "213-218. ",
                            url = "Recuperado de https://psycnet.apa.org/record/1968-03998-001",
                            resourceType = 5,
                            description = "Artículo de revista digital"
                            ),
                        DigitalExercises(
                            id = 9,
                            authors = "Morutwa, G., Plattner, I. E. ",
                            year = "(2014). ",
                            title = "Self-control and alcohol consumption among university students in Botswana. ",
                            journal = "African Journal of Drug and Alcohol Studies, ",
                            issue = "13, ",
                            pages = "69-78. ",
                            url = "Recuperado de https://www.ajol.info/index.php/ajdas/article/view/114196",
                            resourceType = 5,
                            description = "Artículo de revista digital"
                            ),
                        DigitalExercises(
                            id = 10,
                            authors = "Escudero, J. L. ",
                            date = "(16 de junio de 2017). ",
                            title = "Ola de calor, se sobrepasarán los 43 grados ",
                            url = "Recuperado de http://blogs.diariosur.es/tormentas-y-rayos/2017/06/16/ola-de-calor-se-sobrepasaran-los-43oc/",
                            resourceType = 6,
                            exerciseType = 2,
                            description = "Entrada de blog"
                            ),
                        DigitalExercises(
                            id = 11,
                            authors = "Mendoza, J. ",
                            date = "(13 de marzo de 2010). ",
                            title = "Las placas tectónicas de la Tierra ",
                            url = "Recuperado de https://imagnalia.wordpress.com/2010/03/13/las-placas-tectonicas-de-la-tierra/",
                            resourceType = 6,
                            description = "Entrada de blog"
                            ),
                        DigitalExercises(
                            id = 12,
                            authors = "Sala i Martin, X. ",
                            date = "(30 de noviembre, 2016). ",
                            tweet = "Mañana hablaré en Bucaramanga sobre diversificación de economías que dependen de recursos naturales... Como la de Santander (Colombia) ",
                            url = "Recuperado de https://twitter.com/XSalaimartin/status/803982498107719680?lang=en",
                            resourceType = 7,
                            exerciseType = 2,
                            description = "Tuit"
                            ),
                        DigitalExercises(
                            id = 13,
                            authors = "Gates, B. ",
                            date = "(23 de julio, 2015). ",
                            tweet = "For poor countries, development isn't just about foreign aid. Investment and taxes matter just as much, if not more: b-gat.es/1lfVdn6 ",
                            url = "Recuperado de https://www.twitter.com/zuck/posts/10102217102231151",
                            resourceType = 7,
                            exerciseType = 2,
                            description = "Tuit"
                            ),
                        DigitalExercises(
                            id = 14,
                            authors = "Arnalds, O. ",
                            date = "(8 de marzo de 2013). ",
                            title = "Improvisations ",
                            url = "Recuperado de https://www.youtube.com/watch?v=JnmZdTiyE-Qyt=910s",
                            resourceType = 8,
                            description = "Video"
                            ),
                        DigitalExercises(
                            id = 15,
                            authors = "La Liga Santander ",
                            date = "(27 de enero de 2013). ",
                            title = "Los diez mejores goles del Málago CF en la temporada 2011/2012 ",
                            url = "Recuperado de https://www.youtube.com/watch?v=EMkkj888Vv0",
                            resourceType = 8,
                            description = "Video"
                            ),
                        DigitalExercises(
                            id = 16,
                            authors = "Salgado Mella, C. ",
                            date = "(2007). ",
                            title = "Paisaje nevado ",
                            url = "Recuperado de https://commons.wikimedia.org/wiki/File:Paisaje_nevado_-_panoramio.jpg?uselang=es",
                            resourceType = 9,
                            description = "Imágenes"
                            ),
                        DigitalExercises(
                            id = 17,
                            authors = "Stanmeyer, J. ",
                            date = "(26 de febrero, 2014). ",
                            title = "Signal ",
                            url = "Recuperado de http://www.worldpressphoto.org/collection/photo/2014/contemporary-issues/john-stanmeyer",
                            resourceType = 9,
                            description = "Imágenes"
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