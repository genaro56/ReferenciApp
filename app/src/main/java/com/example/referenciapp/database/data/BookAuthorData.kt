package com.example.referenciapp.database.data

import com.example.referenciapp.database.BookAuthor

class BookAuthorData {
    fun populateBookAuthorData(): List<BookAuthor> {
        return listOf(
            BookAuthor(
                bookId = 0,
                authorId = 0
            ),
            BookAuthor(
                bookId = 0,
                authorId = 1
            ),
            BookAuthor(
                bookId = 1,
                authorId = 2
            ),
            BookAuthor(
                bookId = 2,
                authorId = 3
            )
        )
    }
}