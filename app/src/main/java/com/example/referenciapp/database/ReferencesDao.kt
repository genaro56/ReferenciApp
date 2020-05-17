package com.example.referenciapp.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ReferencesDao {
    @Transaction
    @Query("SELECT * FROM Book")
    fun getBooksWithAuthor(): List<BookWithAuthors>

    @Transaction
    @Query("SELECT * FROM Book")
    fun getBooksWithExercises(): List<BookWithExercises>
}