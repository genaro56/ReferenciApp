package com.app.referenciapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ReferenceDao {
    // Getting all exercises
    @Query("SELECT * FROM PrintExercises")
    fun getPrintExercises(): LiveData<List<PrintExercises>>

    @Query("SELECT * FROM DigitalExercises")
    fun getDigitalExercises(): LiveData<List<DigitalExercises>>

    // Getting a specific exercise
    @Query("SELECT * FROM PrintExercises WHERE id = :printId")
    fun getPrintExercise(printId: Long): LiveData<PrintExercises>

    @Query("SELECT * FROM DigitalExercises WHERE id = :digitalId")
    fun getDigitalExercise(digitalId: Long): LiveData<DigitalExercises>

    // Loading data
    @Insert
    suspend fun insertPrintAll(vararg ex: PrintExercises)

    @Insert
    suspend fun insertDigitalAll(vararg ex: DigitalExercises)

    // Updating data (e.g. when an exercise is complete
    @Update
    suspend fun updatePrint(vararg ex: PrintExercises?)

    @Update
    suspend fun updateDigital(vararg ex: DigitalExercises?)
}