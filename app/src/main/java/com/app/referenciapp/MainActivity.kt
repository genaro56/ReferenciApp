package com.app.referenciapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.app.referenciapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var referenceViewModel: ReferenceMenuViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        referenceViewModel = ViewModelProvider(this).get(ReferenceMenuViewModel::class.java)
    }
}
