package com.example.referenciapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.referenciapp.databinding.ActivityMainBinding
import com.example.referenciapp.recycler.PrintExerciseListAdapter

class MainActivity : AppCompatActivity() {

    lateinit var referenceViewModel: ReferenceMenuViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        referenceViewModel = ViewModelProvider(this).get(ReferenceMenuViewModel::class.java)

        setSupportActionBar(binding.topAppBar)

    }
}
