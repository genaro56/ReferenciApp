package com.example.referenciapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.referenciapp.database.DigitalExercises
import com.example.referenciapp.database.PrintExercises
import com.example.referenciapp.database.ReferenceDatabase
import com.example.referenciapp.database.ReferenceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReferenceMenuViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ReferenceRepository

    val allPrintExercises: LiveData<List<PrintExercises>>
    val allDigitalExercises: LiveData<List<DigitalExercises>>

    init {
        val referenceDao = ReferenceDatabase.getDatabase(application, viewModelScope).referenceDao()
        repository = ReferenceRepository(referenceDao)
        allPrintExercises = repository.allPrintExercises
        allDigitalExercises = repository.allDigitalExercises
    }

    fun insertPrint(ex: PrintExercises) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertPrint(ex)
    }

    fun insertDigital(ex: DigitalExercises) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertDigital(ex)
    }

    fun updatePrint(ex: PrintExercises) = viewModelScope.launch(Dispatchers.IO) {
        repository.updatePrint(ex)
    }

    fun updateDigital(ex: DigitalExercises) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateDigital(ex)
    }
}