package com.example.referenciapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    // Instead of passing around data in bundles, we'll update the selected index with
    // LiveData. Since the id's match the exercises' place in the List, we can access any
    // exercises with an index, rather than relying on queries to the db.
    private val _selectedId = MutableLiveData<Long>()
    val selectedId: LiveData<Long>
        get() = _selectedId

    init {
        val referenceDao = ReferenceDatabase.getDatabase(application, viewModelScope).referenceDao()
        repository = ReferenceRepository(referenceDao)
        allPrintExercises = repository.allPrintExercises
        allDigitalExercises = repository.allDigitalExercises
        _selectedId.value = 0
    }

    fun setSelectedId(id: Long) {
        _selectedId.value = id
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