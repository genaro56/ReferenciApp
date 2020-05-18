package com.example.referenciapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.referenciapp.database.data.AuthorData
import com.example.referenciapp.database.data.BookAuthorData
import com.example.referenciapp.database.data.BookData
import com.example.referenciapp.database.data.BookExerciseData

@Database(
    entities = [Book::class, Author::class, BookAuthor::class, BookExercise::class],
    version = 1,
    exportSchema = true)
abstract class ReferenceDatabase : RoomDatabase() {

    abstract val referenceDao: ReferencesDao

    companion object {

        @Volatile
        private var INSTANCE: ReferenceDatabase? = null

        fun getInstance(context: Context): ReferenceDatabase {
            // Multiple threads can ask for the database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchronized block at a
            // time.
            synchronized(this) {
                // Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                // Smart cast is only available to local variables.
                var instance = INSTANCE
                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ReferenceDatabase::class.java,
                        "referenciapp_database"
                    )
                        .addCallback(object: Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                val bookData = BookData()
                                val authorData = AuthorData()
                                val bookAuthorData = BookAuthorData()
                                val bookExerciseData = BookExerciseData()

                                ioThread {
                                    var dao = getInstance(context).referenceDao

                                    dao.insertAuthorData(authorData.populateAuthorData())
                                    dao.insertBookData(bookData.populateBookData())
                                    dao.insertBookAuthorData(bookAuthorData.populateBookAuthorData())
                                    dao.insertBookExerciseData(bookExerciseData.populateBookExerciseData())
                                }
                            }
                        })
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // Migration is not part of this lesson. You can learn more about
                        // migration with Room in this blog post:
                        // https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
//                        .fallbackToDestructiveMigration()
                        .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}
