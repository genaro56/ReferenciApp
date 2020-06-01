package com.app.referenciapp.database

import androidx.lifecycle.LiveData

class ReferenceRepository(private val referenceDao: ReferenceDao) {

    val allPrintExercises: LiveData<List<PrintExercises>> = referenceDao.getPrintExercises()
    val allDigitalExercises: LiveData<List<DigitalExercises>> = referenceDao.getDigitalExercises()

    suspend fun insertPrint(vararg ex: PrintExercises) {
        referenceDao.insertPrintAll(*ex)
    }

    suspend fun insertDigital(vararg ex: DigitalExercises) {
        referenceDao.insertDigitalAll(*ex)
    }

    suspend fun updatePrint(ex: PrintExercises) {
        referenceDao.updatePrint(ex)
    }

    suspend fun updateDigital(ex: DigitalExercises) {
        referenceDao.updateDigital(ex)
    }
}