package com.example.referenciapp.database

import androidx.room.*

@Entity
data class Book(
   @PrimaryKey(autoGenerate = true) val bookId: Long = 0L,

   var title: String = "",
   var year: Int = 0,
   var month: String = "",
   var day: String = "",
   var publisher: String = "",
   var city: String = "",
   var pages: String = ""
)

@Entity
data class Author(
   @PrimaryKey(autoGenerate = true) val authorId: Long = 0L,

   var firstName: String = "",
   var lastName: String = ""
)

@Entity(primaryKeys = ["bookId", "authorId"])
data class BookAuthor(
   val bookId: Long,
   val authorId: Long
)

@Entity
data class BookExercise(
   @PrimaryKey(autoGenerate = true) val exerciseId: Long = 0L,
   val bookExId: Long,
   var exerciseTitle: String = "",
   var completed: Boolean = false
)

data class BookWithExercises(
   @Embedded val book: Book,
   @Relation(
      parentColumn = "bookId",
      entityColumn = "bookExId"
   )
   val exercises: List<BookExercise>
)

data class BookWithAuthors(
   @Embedded val book: Book,
   @Relation(
      parentColumn = "bookId",
      entityColumn = "authorId",
      associateBy = Junction(BookAuthor::class)
   )
   val authors: List<Author>
)

