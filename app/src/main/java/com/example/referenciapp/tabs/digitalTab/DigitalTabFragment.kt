package com.example.referenciapp.tabs.digitalTab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.referenciapp.R
import com.example.referenciapp.ReferenceMenuViewModel
import com.example.referenciapp.databinding.FragmentDigitalTabBinding
import com.example.referenciapp.recycler.DigitalExerciseListAdapter

class DigitalTabFragment : Fragment() {

    private lateinit var referenceViewModel: ReferenceMenuViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentDigitalTabBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_digital_tab,
            container,
            false
        )

        // We use requireActivity() to use the shared ViewModel.
        referenceViewModel = ViewModelProvider(requireActivity()).get(ReferenceMenuViewModel::class.java)

        val recyclerView = binding.digitalTabRecycler
        val adapter = DigitalExerciseListAdapter(requireNotNull(context), referenceViewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireNotNull(context))

        referenceViewModel.allDigitalExercises.observe(viewLifecycleOwner, Observer { exercises ->
            exercises?.let { adapter.setExercises(it)}
        })
        return binding.root
    }
}

