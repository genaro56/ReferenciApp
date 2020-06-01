package com.app.referenciapp.screens.listMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.referenciapp.R
import com.app.referenciapp.databinding.FragmentListMenuBinding

class ListMenuFragment : Fragment() {

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

        val supportActionBar = (activity as AppCompatActivity).supportActionBar
        supportActionBar?.title = "Sobre Referenciapp"

        return binding.root
    }

}
