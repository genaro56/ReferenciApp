package com.example.referenciapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.referenciapp.databinding.FragmentExerciseBinding

/**
 * A simple [Fragment] subclass.
 */
class ExerciseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentExerciseBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_exercise,
            container,
            false
        )

        return binding.root
    }

}
