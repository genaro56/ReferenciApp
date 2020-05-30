package com.example.referenciapp.screens.exercise

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.referenciapp.ExerciseViewModel
import com.example.referenciapp.R
import com.example.referenciapp.ReferenceMenuViewModel
import com.example.referenciapp.database.DigitalExercises
import com.example.referenciapp.database.PrintExercises
import com.example.referenciapp.databinding.FragmentExerciseBinding
import com.example.referenciapp.recycler.ReferenceFieldsListAdapter
import java.util.*

@Suppress("DEPRECATION")
class ExerciseFragment : Fragment() {

    private lateinit var appViewModel: ReferenceMenuViewModel
    private lateinit var exerciseViewModel: ExerciseViewModel
    private lateinit var adapter: ReferenceFieldsListAdapter

    val dragAndDrop =
        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition

                Collections.swap(exerciseViewModel.attributes.value!!, fromPosition, toPosition)
                adapter.notifyItemMoved(fromPosition, toPosition)

                return false
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                exerciseViewModel.setAttrs(
                    exerciseViewModel.attributes.value!!
                )
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) { }
    })

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
        adapter = ReferenceFieldsListAdapter(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        dragAndDrop.attachToRecyclerView(recyclerView)

        // Unpack the current exercise
        val currentExercise: Any
        val correctAnswer: List<String>
        var attrs: List<String>
        if(appViewModel.resourceType.value == 0) {
            // It is a print exercise
            currentExercise = appViewModel.currentPrintExercise.value!!
            correctAnswer = extractPrintFields(currentExercise)
            attrs = correctAnswer

            while(attrs == correctAnswer) {
                attrs = attrs.shuffled()
            }

            exerciseViewModel.setAttrs(
                attrs
            )
        }
        else {
            // It is a digital exercise
            currentExercise = appViewModel.currentDigitalExercise.value!!
            correctAnswer = extractDigitalFields(currentExercise)
            attrs = correctAnswer

            while(attrs == correctAnswer) {
                attrs = attrs.shuffled()
            }

            exerciseViewModel.setAttrs(
                attrs
            )
        }


        // Observe the current exercise attributes to update the RecyclerView
        exerciseViewModel.attributes.observe(viewLifecycleOwner, Observer { attributes ->
            attributes?.let { adapter.setAttributes(attributes) }
            checkAnswer(correctAnswer, attributes)
        })

        return binding.root
    }

    private fun checkAnswer(correctAnswer: List<String>, attributes: List<String>?) {
        if(correctAnswer == attributes) {
            Toast.makeText(
                context,
                "¡Excelente!",
                Toast.LENGTH_SHORT
            ).show()
        }
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

        return fields.filter { x -> x != "" }
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

        return fields.filter { x -> x != "" }
    }
}
