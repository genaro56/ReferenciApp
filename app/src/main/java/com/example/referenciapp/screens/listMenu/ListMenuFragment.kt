package com.example.referenciapp.screens.listMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.referenciapp.R
import com.example.referenciapp.databinding.FragmentListMenuBinding

class ListMenuFragment : Fragment() {

    lateinit var listExercisesRecycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentListMenuBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list_menu,
            container,
            false
        )

//        listExercisesRecycler = binding.listExercisesRecycler
//        listExercisesRecycler.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = ListSelectionRecyclerViewAdapter()
//        }

        return binding.root
    }

}
