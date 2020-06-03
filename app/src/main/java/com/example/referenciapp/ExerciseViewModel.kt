package com.example.referenciapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class ExerciseViewModel(application: Application): AndroidViewModel(application) {
    val attributes = MutableLiveData<List<String>>()

    fun setAttrs(attrs: List<String>?) {
        attributes.value = attrs
    }
}