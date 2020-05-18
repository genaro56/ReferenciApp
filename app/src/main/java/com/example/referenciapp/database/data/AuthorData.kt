package com.example.referenciapp.database.data

import com.example.referenciapp.database.Author

class AuthorData {
    fun populateAuthorData(): List<Author> {
        return listOf(
            Author(
                authorId = 0,
                firstName = "Antonio",
                lastName = "Mendoza Fillola"
            ),
            Author(
                authorId = 1,
                firstName = "Ezequiel",
                lastName = "Briz Villanueva"
            ),
            Author(
                authorId = 2,
                firstName = "Yuri G",
                lastName = "Gurevich"
            ),
            Author(
                authorId = 3,
                firstName = "Antonio José del",
                lastName = "Puig"
            )
        )
    }
}