package com.app.referenciapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.referenciapp.database.DigitalExercises
import com.app.referenciapp.database.PrintExercises
import com.app.referenciapp.database.ReferenceDatabase
import com.app.referenciapp.database.ReferenceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReferenceMenuViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ReferenceRepository
//    exercises LiveData Lists
//    allPrintExercise.value[index] to access
    val allPrintExercises: LiveData<List<PrintExercises>>
    val allDigitalExercises: LiveData<List<DigitalExercises>>

    // Instead of passing around data in bundles, we'll update the selected index with
    // LiveData. Since the id's match the exercises' place in the List, we can access any
    // exercises with an index, rather than relying on queries to the db.
    // Likewise, we can specify if the clicked item is a print or digital exercise, and so on.
    // The advantage is that ViewModels are lifecycle aware, and we don't have to worry about
    // configuration changes.
    private val _selectedId = MutableLiveData<Int>()
    val selectedId: LiveData<Int>
        get() = _selectedId

    // 0: print, 1: digital
    private val _resourceType = MutableLiveData<Int>()
    val resourceType: LiveData<Int>
        get() = _resourceType

    private val _currentPrintExercise = MutableLiveData<PrintExercises>()
    val currentPrintExercise: LiveData<PrintExercises>
        get() = _currentPrintExercise

    private val _currentDigitalExercise = MutableLiveData<DigitalExercises>()
    val currentDigitalExercise: LiveData<DigitalExercises>
        get() = _currentDigitalExercise

    init {
        val referenceDao = ReferenceDatabase.getDatabase(application, viewModelScope).referenceDao()
        repository = ReferenceRepository(referenceDao)
        allPrintExercises = repository.allPrintExercises
        allDigitalExercises = repository.allDigitalExercises
        _selectedId.value = 0
    }

    fun setSelectedId(id: Int) {
        _selectedId.value = id
    }

    fun setResourceType(type: Int) {
        _resourceType.value = type
        if(_resourceType.value == 0) { // i.e. we clicked on a print exercise
            _currentPrintExercise.value = allPrintExercises.value?.get(_selectedId.value!!)
        }
        else
            _currentDigitalExercise.value = allDigitalExercises.value?.get(_selectedId.value!!)

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