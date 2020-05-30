package com.example.referenciapp.screens.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.referenciapp.ExerciseViewModel
import com.example.referenciapp.R
import com.example.referenciapp.ReferenceMenuViewModel
import com.example.referenciapp.database.DigitalExercises
import com.example.referenciapp.database.PrintExercises
import com.example.referenciapp.databinding.FragmentExerciseBinding
import com.example.referenciapp.recycler.ReferenceFieldsListAdapter

@Suppress("DEPRECATION")
class ExerciseFragment : Fragment() {

    private lateinit var appViewModel: ReferenceMenuViewModel
    private lateinit var exerciseViewModel: ExerciseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentExerciseBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_exercise,
            container,
            false
        )

        // Set the View Models to observe the selected exercise and update the recycler view
        appViewModel =
            ViewModelProvider(requireNotNull(activity)).get(ReferenceMenuViewModel::class.java)

        exerciseViewModel =
            ViewModelProvider(requireNotNull(activity)).get(ExerciseViewModel::class.java)

        // Setup the recycler view
        val recyclerView = binding.referenceFieldsRecycler
        val adapter = ReferenceFieldsListAdapter(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Unpack the current exercise
        val currentExercise: Any
        if(appViewModel.resourceType.value == 0) {
            // It is a print exercise
            currentExercise = appViewModel.currentPrintExercise.value!!
            exerciseViewModel.setAttrs(
                extractPrintFields(currentExercise)
            )
        }
        else {
            // It is a digital exercise
            currentExercise = appViewModel.currentDigitalExercise.value!!
            exerciseViewModel.setAttrs(
                extractDigitalFields(currentExercise)
            )
        }


        // Observe the current exercise attributes to update the RecyclerView
        exerciseViewModel.attributes.observe(viewLifecycleOwner, Observer { attributes ->
            attributes?.let { adapter.setAttributes(attributes) }
        })

        return binding.root
    }

    private fun extractDigitalFields(currentExercise: DigitalExercises): List<String> {
        var fields = emptyList<String>()

        when (currentExercise.resourceType) {
            1 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.year,
                    currentExercise.title,
                    currentExercise.url
                )
            }
            2 -> {
                fields = listOf<String>(
                    currentExercise.term,
                    currentExercise.year,
                    currentExercise.institution,
                    currentExercise.edition,
                    currentExercise.source
                )
            }
        }

        return fields
    }

    private fun extractPrintFields(currentExercise: PrintExercises): List<String> {
        var fields = emptyList<String>()

        when (currentExercise.resourceType) {
            1 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.year,
                    currentExercise.editors,
                    currentExercise.title,
                    currentExercise.city,
                    currentExercise.country,
                    currentExercise.publisher
                )
            }
            2 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.year,
                    currentExercise.editors,
                    currentExercise.title,
                    currentExercise.city,
                    currentExercise.country,
                    currentExercise.publisher,
                    currentExercise.chapterTitle,
                    currentExercise.editors,
                    currentExercise.pages
                )
            }
            3 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.year,
                    currentExercise.city,
                    currentExercise.term,
                    currentExercise.source,
                    currentExercise.edition,
                    currentExercise.publisher
                )
            }
            4 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.year,
                    currentExercise.title,
                    currentExercise.city,
                    currentExercise.source,
                    currentExercise.country,
                    currentExercise.publisher,
                    currentExercise.chapterTitle,
                    currentExercise.editors,
                    currentExercise.volumeAndPages,
                    currentExercise.term
                )
            }
            5 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.date,
                    currentExercise.title,
                    currentExercise.newspaper,
                    currentExercise.pages
                )
            }
            6 -> {
                fields = listOf<String>(
                    currentExercise.authors,
                    currentExercise.year,
                    currentExercise.title,
                    currentExercise.journal,
                    currentExercise.volume,
                    currentExercise.issue,
                    currentExercise.pages
                )
            }
        }

        return fields
    }
}
