package com.example.referenciapp.tabs.digitalTab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.referenciapp.R
import com.example.referenciapp.databinding.FragmentDigitalTabBinding
import com.example.referenciapp.recycler.ListSelectionRecyclerViewAdapter

class DigitalTabFragment : Fragment() {

    lateinit var exerciseRecycler: RecyclerView
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

        // Navigation to Exercise Fragment
        // This is only for testing/MVP purposes. Eventually we'll add
        // more exercise entries
//        binding.exerciseButton.setOnClickListener(
//            Navigation.
//                createNavigateOnClickListener(
//                    R.id.action_referenceMenuFragment_to_exerciseFragment
//                )
//        )


        exerciseRecycler = binding.digitalTabRecycler
        exerciseRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ListSelectionRecyclerViewAdapter()
        }

        return binding.root
    }
}

