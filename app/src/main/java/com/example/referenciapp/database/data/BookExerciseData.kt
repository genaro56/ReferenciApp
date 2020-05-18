package com.example.referenciapp.database.data

import com.example.referenciapp.database.BookExercise

class BookExerciseData {
    fun populateBookExerciseData(): List<BookExercise> {
        return listOf(
            BookExercise(
                exerciseTitle = "Libro 1, múltiples autores",
                bookExId = 0
            ),
            BookExercise(
                exerciseTitle = "Libro 2, dos nombres",
                bookExId = 1
            ),
            BookExercise(
                exerciseTitle = "Libro 3, nombre con minúscula",
                bookExId = 2
            )
        )
    }
}