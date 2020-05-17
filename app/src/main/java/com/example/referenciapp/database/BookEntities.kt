package com.example.referenciapp.database

import androidx.room.*

@Entity
data class Book(
   @PrimaryKey(autoGenerate = true) val bookId: Long,

   var title: String = "",
   var year: Int = 0,
   var month: String = "",
   var day: String = "",
   var publisher: String = "",
   var pages: String = ""
)

@Entity
data class Author(
   @PrimaryKey(autoGenerate = true) val authorId: Long,

   var firstName: String = "",
   var lastName: String = ""
)

@Entity(primaryKeys = ["bookId", "authorId"])
data class BookAuthor(
   val bookId: Long,
   val authorId: Long
)

data class BookWithAuthors(
   @Embedded val book: Book,
   @Relation(
      parentColumn = "bookId",
      entityColumn = "authorId",
      associateBy = Junction(BookAuthor::class)
   )
   val songs: List<Author>
)

