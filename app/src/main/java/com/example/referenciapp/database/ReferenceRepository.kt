package com.example.referenciapp.database

import androidx.lifecycle.LiveData

class ReferenceRepository(private val referenceDao: ReferenceDao) {

    val allPrintExercises: LiveData<List<PrintExercises>> = referenceDao.getPrintExercises()
    val allDigitalExercises: LiveData<List<DigitalExercises>> = referenceDao.getDigitalExercises()

    suspend fun insertPrint(ex: PrintExercises) {
        referenceDao.insertPrint(ex)
    }

    suspend fun insertDigital(ex: DigitalExercises) {
        referenceDao.insertDigital(ex)
    }

    suspend fun updatePrint(ex: PrintExercises) {
        referenceDao.updatePrint(ex)
    }

    suspend fun updateDigital(ex: DigitalExercises) {
        referenceDao.updateDigital(ex)
    }
}