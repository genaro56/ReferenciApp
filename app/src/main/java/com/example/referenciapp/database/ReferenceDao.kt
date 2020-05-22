package com.example.referenciapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ReferenceDao {
    // Getting all exercises
    @Query("SELECT * FROM PrintExercises")
    fun getPrintExercises(): List<PrintExercises>

    @Query("SELECT * FROM DigitalExercises")
    fun getDigitalExercises(): List<DigitalExercises>

    // Getting a specific exercise
    @Query("SELECT * FROM PrintExercises WHERE id = :printId")
    fun getPrintExercise(printId: PrintExercises)

    @Query("SELECT * FROM DigitalExercises WHERE id = :digitalId")
    fun getDigitalExercise(digitalId: DigitalExercises)

    // Loading data
    @Insert
    suspend fun insertPrint(ex: PrintExercises)

    @Insert
    suspend fun insertDigital(ex: DigitalExercises)

    // Updating data (e.g. when an exercise is complete
    @Update
    suspend fun updatePrint(vararg ex: PrintExercises)

    @Update
    suspend fun updateDigital(vararg ex: DigitalExercises)
}