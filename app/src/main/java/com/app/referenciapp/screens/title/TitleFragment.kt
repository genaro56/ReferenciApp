package com.app.referenciapp.screens.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.app.referenciapp.R
import com.app.referenciapp.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout manually, since this is a fragment
        val binding : FragmentTitleBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_title,
            container,
            false
        )

        // Set onClick listener for the references menu button
        // Overall steps: (this code skips some things thanks to Android KTX)
        // 1. Find the NavHostFragment Navigation Controller
        // 2. Use navigate() with the Action created in the navigation resource graph
        binding.referenceMenuCardButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_titleFragment_to_referenceMenuFragment
            )
        )

        // Same, but for the list of references button
        binding.aboutMenuButton.setOnClickListener(
            Navigation.
                createNavigateOnClickListener(
                    R.id.action_titleFragment_to_listMenuFragment
                )
        )

        val supportActionBar = (activity as AppCompatActivity).supportActionBar
        supportActionBar?.title = "Referenciapp"

        return binding.root
    }

}
