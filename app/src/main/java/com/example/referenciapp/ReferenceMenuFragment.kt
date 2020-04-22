package com.example.referenciapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.referenciapp.databinding.FragmentReferenceMenuBinding

/**
 * A simple [Fragment] subclass.
 */
class ReferenceMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentReferenceMenuBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_reference_menu,
            container,
            false
        )

        // Navigation to Exercise Fragment
        // This is only for testing/MVP purposes. Eventually we'll add
        // more exercise entries
        binding.exerciseButton.setOnClickListener(
            Navigation.
                createNavigateOnClickListener(
                    R.id.action_referenceMenuFragment_to_exerciseFragment
                )
        )

        return binding.root
    }

}
