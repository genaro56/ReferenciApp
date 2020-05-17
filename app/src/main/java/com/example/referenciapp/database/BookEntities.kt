package com.example.referenciapp.database

import androidx.room.*

@Entity
data class Book(
   @PrimaryKey(autoGenerate = true) var id: Long,

   var title: String = "",
   var year: Int = 0,
   var month: String = "",
   var day: String = "",
   var publisher: String = "",
   var pages: String = ""
)

@Entity
data class Author(
   @PrimaryKey(autoGenerate = true) var id: Long,

   var firstName: String = "",
   var lastName: String = ""
)

@Entity(primaryKeys = ["bookId", "authorId"])
data class BookAuthor(
   var bookId: Long,
   var authorId: Long
)

data class BookWithAuthors(
   @Embedded val book: Book,
   @Relation(
      parentColumn = "playlistId",
      entityColumn = "songId",
      associateBy = Junction(BookAuthor::class)
   )
   val songs: List<Author>
)

