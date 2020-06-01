package com.app.referenciapp.screens.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.referenciapp.R
import com.app.referenciapp.ReferenceMenuViewModel
import com.app.referenciapp.database.DigitalExercises
import com.app.referenciapp.database.PrintExercises
import com.app.referenciapp.databinding.FragmentExercise2Binding
import kotlinx.android.synthetic.main.fragment_exercise2.*

data class Question(var question: String, var answer: Boolean)

class ExerciseFragment2 : Fragment() {
    private lateinit var viewModel: ReferenceMenuViewModel
    private val listOfAttributes = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentExercise2Binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_exercise2,
            container,
            false
        )
        val context = activity as AppCompatActivity

        // Buttons and UI
        val trueButton: Button = binding.trueButton
        val falseButton: Button = binding.falseButton
        val questionTitle = binding.questions
        val referenceType = binding.referenceType2

        // Set the View Model to observe the selected exercise.
        viewModel =
            ViewModelProvider(requireNotNull(activity)).get(ReferenceMenuViewModel::class.java)

        val currentReference: Any

        if (viewModel.resourceType.value == 0) {
            // The exercise is of print type
            currentReference = viewModel.currentPrintExercise.value!!
            referenceType.text = currentReference.description
            when (currentReference.resourceType) {
                1 -> {
                    listOfAttributes += listOf<String>(
                        currentReference.authors,
                        currentReference.year,
                        currentReference.editors,
                        currentReference.title,
                        currentReference.city,
                        currentReference.country,
                        currentReference.publisher
                    )
                }
                2 -> {
                    listOfAttributes += listOf<String>(
                        currentReference.authors,
                        currentReference.year,
                        currentReference.editors,
                        currentReference.title,
                        currentReference.city,
                        currentReference.country,
                        currentReference.publisher,
                        currentReference.chapterTitle,
                        currentReference.editors,
                        currentReference.pages
                    )
                }
                3 -> {
                    listOfAttributes += listOf<String>(
                        currentReference.authors,
                        currentReference.year,
                        currentReference.city,
                        currentReference.term,
                        currentReference.source,
                        currentReference.edition,
                        currentReference.publisher
                    )
                }
                4 -> {
                    listOfAttributes += listOf<String>(
                        currentReference.authors,
                        currentReference.year,
                        currentReference.title,
                        currentReference.city,
                        currentReference.source,
                        currentReference.country,
                        currentReference.publisher,
                        currentReference.chapterTitle,
                        currentReference.editors,
                        currentReference.volumeAndPages,
                        currentReference.term
                    )
                }
                5 -> {
                    listOfAttributes += listOf<String>(
                        currentReference.authors,
                        currentReference.date,
                        currentReference.title,
                        currentReference.newspaper,
                        currentReference.pages
                    )
                }
                6 -> {
                    listOfAttributes += listOf<String>(
                        currentReference.authors,
                        currentReference.year,
                        currentReference.title,
                        currentReference.journal,
                        currentReference.volume,
                        currentReference.issue,
                        currentReference.pages
                    )
                }
                // TODO: unwrap print exercise object
            }
            // TODO: unwrap print exercise object

        } else {
            // The exercise is of digital type
            currentReference = viewModel.currentDigitalExercise.value!!
            referenceType.text = currentReference.description
            when (currentReference.resourceType) {
                1 -> {
                    listOfAttributes += listOf<String>(
                        currentReference.authors,
                        currentReference.year,
                        currentReference.title,
                        currentReference.url
                    )
                }
                2 -> {
                    listOfAttributes += listOf<String>(
                        currentReference.term,
                        currentReference.year,
                        currentReference.institution,
                        currentReference.edition,
                        currentReference.source
                    )
                }
            }
        }

        // Concatenate list of attributes
        val concatQuestion = ReferenceUtils.concatReference(listOfAttributes)
        val shuffledQuestion = if ((0..10).random().rem(2) == 0) ReferenceUtils.concatReference(
            ReferenceUtils.shuffleList(listOfAttributes)
        ) else concatQuestion

        // set exercise question
        questionTitle.text = shuffledQuestion

        //  set exercise title
        val referenceTitleTextView = binding.referenceTitle
        referenceTitleTextView.text = arguments?.getString("title")

        fun answerQuestion(selectedAnswer: Boolean) {
            if (selectedAnswer) {
                if (concatQuestion == shuffledQuestion) {
                    Toast.makeText(
                        context,
                        "¡Correcto! La referencia está bien construida.",
                        Toast.LENGTH_LONG
                    ).show()

                    // The question was successfully completed. Update the DB
                    updateDB()
                } else {
                    Toast.makeText(context, "¡Respuesta incorrecta!", Toast.LENGTH_LONG).show()
                    questions.text = "Respuesta correcta: " + concatQuestion
                }
            } else if (!selectedAnswer) {
                if (concatQuestion != shuffledQuestion) {
                    Toast.makeText(
                        context,
                        "¡Respuesta correcta! La referencia tiene errores.",
                        Toast.LENGTH_LONG
                    ).show()

                    // The question was successfully completed. Update the DB
                    updateDB()
                } else {
                    Toast.makeText(context, "¡Respuesta incorrecta!", Toast.LENGTH_LONG).show()
                    questions.text = "Respuesta correcta: " + concatQuestion
                }
            }
        }

        falseButton.setOnClickListener {
            answerQuestion(false)
        }

        trueButton.setOnClickListener {
            answerQuestion(true)
        }
        return binding.root
    }

    private fun updateDB() {
        if (viewModel.resourceType.value == 0) {
            val updatedExercise: PrintExercises = viewModel.currentPrintExercise.value!!
            updatedExercise.completed = true
            viewModel.updatePrint(updatedExercise)
        } else if (viewModel.resourceType.value == 1) {
            val updatedExercise: DigitalExercises = viewModel.currentDigitalExercise.value!!
            updatedExercise.completed = true
            viewModel.updateDigital(updatedExercise)
        }
    }
}
