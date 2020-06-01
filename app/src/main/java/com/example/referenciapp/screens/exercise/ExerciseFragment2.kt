package com.example.referenciapp.screens.exercise

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.referenciapp.ExerciseViewModel
import com.example.referenciapp.R
import com.example.referenciapp.ReferenceMenuViewModel
import com.example.referenciapp.database.DigitalExercises
import com.example.referenciapp.database.PrintExercises
import com.example.referenciapp.databinding.FragmentExercise2Binding
import com.example.referenciapp.databinding.FragmentExerciseBinding
import com.example.referenciapp.databinding.FragmentExerciseBindingImpl
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_exercise2.*
import kotlin.math.roundToInt
import kotlin.random.Random

class ExerciseFragment2 : Fragment() {
    private lateinit var appViewModel: ReferenceMenuViewModel
    private lateinit var exerciseViewModel: ExerciseViewModel
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

        // Setup the title in the support action bar
        val supportActionBar = context.supportActionBar

        // Set the View Model to observe the selected exercise.
        appViewModel =
            ViewModelProvider(requireNotNull(activity)).get(ReferenceMenuViewModel::class.java)

        exerciseViewModel =
            ViewModelProvider(requireNotNull(activity)).get(ExerciseViewModel::class.java)

        // Unpack the current exercise
        val currentExercise: Any
        val correctAnswer: List<String>
        val attrs: List<String>
        if(appViewModel.resourceType.value == 0) {
            // It is a print exercise
            currentExercise = appViewModel.currentPrintExercise.value!!
            titleBarSetup(supportActionBar, currentExercise.id, currentExercise.description)
            correctAnswer = ReferenceUtils.extractPrintFields(currentExercise)
            attrs =
                if ((0..10).random().rem(2) == 0)
                    correctAnswer.shuffled()
                else correctAnswer

            exerciseViewModel.setAttrs(
                attrs
            )
        }
        else {
            // It is a digital exercise
            currentExercise = appViewModel.currentDigitalExercise.value!!
            titleBarSetup(supportActionBar, currentExercise.id, currentExercise.description)
            correctAnswer = ReferenceUtils.extractDigitalFields(currentExercise)
            attrs =
                if ((0..10).random().rem(2) == 0)
                    correctAnswer.shuffled()
                else correctAnswer

            exerciseViewModel.setAttrs(
                attrs
            )
        }

        // Concatenate and display the string
        binding.currentReference.text = ReferenceUtils.concatReference(attrs)

        // Buttons and UI
        val trueButton: Button = binding.trueButton
        val falseButton: Button = binding.falseButton


        // Concatenate list of attributes
        val concatQuestion = ReferenceUtils.concatReference(listOfAttributes)
        val shuffledQuestion = if ((0..10).random().rem(2) == 0) ReferenceUtils.concatReference(
            ReferenceUtils.shuffleList(listOfAttributes)
        ) else concatQuestion

        fun answerQuestion(selectedAnswer: Boolean) {
            if (selectedAnswer) {
                if (concatQuestion == shuffledQuestion) {
                    Toast.makeText(context, "¡Correcto! La referencia está bien construida.", Toast.LENGTH_LONG).show()

                    // The question was successfully completed. Update the DB
                    updateDB()
                } else {
                    Toast.makeText(context, "¡Respuesta incorrecta!", Toast.LENGTH_LONG).show()
                }
            } else if (!selectedAnswer) {
                if (concatQuestion != shuffledQuestion) {
                    Toast.makeText(context, "¡Respuesta correcta! La referencia tiene errores.", Toast.LENGTH_LONG).show()

                    // The question was successfully completed. Update the DB
                    updateDB()
                } else {
                    Toast.makeText(context, "¡Respuesta incorrecta!", Toast.LENGTH_LONG).show()
                }
            }
        }

        trueButton.setOnClickListener {
            checkAnswer(true, correctAnswer, attrs)
        }

        falseButton.setOnClickListener {
            checkAnswer(false, correctAnswer, attrs)

        }

        return binding.root
    }

    private fun checkAnswer(answer: Boolean, correctAnswer: List<String>, attrs: List<String>) {
        if(correctAnswer == attrs) {
            if(answer)
                correct("La ficha de referencia está correctamente construida.")
            else
                incorrect("La ficha de referencia sí está en orden.")
        }
        else {
            if(!answer)
                correct("La ficha está en desorden.")
           else
                incorrect("La ficha está en desorden.")
        }
    }

    private fun correct(msg: String) {
        MaterialAlertDialogBuilder(context)
            .setCancelable(false)
            .setTitle("¡Excelente!")
            .setMessage(msg)
            .setNeutralButton("Atrás") { _, _ ->
                requireView().findNavController().popBackStack()
            }
            .show()

        updateDB()
    }

    private fun incorrect(msg: String) {
        MaterialAlertDialogBuilder(context)
            .setCancelable(false)
            .setTitle("Respuesta incorrecta")
            .setMessage(msg)
            .setNegativeButton("Atrás") { _, _ ->
                requireView().findNavController().popBackStack()
            }
            .setPositiveButton("Reintentar") { _, _ ->
                parentFragmentManager
                    .beginTransaction()
                    .detach(this)
                    .attach(this).commit()
            }
            .show()
    }

    private fun titleBarSetup(supportActionBar: ActionBar?, id: Long, description: String) {
        supportActionBar?.title = "Ejercicio ${id + 1}"
        supportActionBar?.subtitle = description
    }

    private fun updateDB() {
        if(appViewModel.resourceType.value == 0) {
            val updatedExercise: PrintExercises = appViewModel.currentPrintExercise.value!!
            updatedExercise.completed = true
            appViewModel.updatePrint(updatedExercise)
        }
        else if (appViewModel.resourceType.value == 1){
            val updatedExercise: DigitalExercises = appViewModel.currentDigitalExercise.value!!
            updatedExercise.completed = true
            appViewModel.updateDigital(updatedExercise)
        }
    }
}
