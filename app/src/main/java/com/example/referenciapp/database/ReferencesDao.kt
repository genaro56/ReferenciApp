package com.example.referenciapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ReferencesDao {
    // Database prepopulation methods
    @Insert
    fun insertBookData(data: List<Book>)

    @Insert
    fun insertAuthorData(data: List<Author>)

    @Insert
    fun insertBookExerciseData(data: List<BookExercise>)

    @Insert
    fun insertBookAuthorData(data: List<BookAuthor>)

    // Database methods to populate UI
    @Transaction
    @Query("SELECT * FROM Book")
    fun getBooksWithAuthor(): List<BookWithAuthors>

    @Transaction
    @Query("SELECT * FROM Book")
    fun getBooksWithExercises(): List<BookWithExercises>
}