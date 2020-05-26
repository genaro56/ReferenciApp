package com.example.referenciapp.screens.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.referenciapp.R
import com.example.referenciapp.ReferenceMenuViewModel
import com.example.referenciapp.databinding.FragmentExercise2Binding
import com.example.referenciapp.databinding.FragmentExerciseBinding
import com.example.referenciapp.databinding.FragmentExerciseBindingImpl
import kotlinx.android.synthetic.main.fragment_exercise2.*
import kotlin.math.roundToInt
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
data class Question(var question: String, var answer: Boolean)

class ExerciseFragment2 : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var viewModel: ReferenceMenuViewModel
    private var param1: String? = null
    private val listOfAttributes = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

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

//        Buttons
        val trueButton: Button = binding.trueButton
        val falseButton: Button = binding.falseButton

        val questionTitle = binding.questions
        val referenceType = binding.referenceType2
        // Set the View Model to observe the selected exercise.
        viewModel =
            ViewModelProvider(requireNotNull(activity)).get(ReferenceMenuViewModel::class.java)
        val currentReference: Any
        if (viewModel.resourceType.value == 0) {
            Toast.makeText(
                context,
                "Print: ${viewModel.currentPrintExercise.value!!.title}",
                Toast.LENGTH_SHORT
            ).show()

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
            Toast.makeText(
                context,
                "Digital: ${viewModel.currentDigitalExercise.value!!.title}",
                Toast.LENGTH_SHORT
            ).show()
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
                // TODO: unwrap print exercise object
            }
        }
//        concatenate list of attributes
        val concatQuestion = ReferenceUtils.concatReference(listOfAttributes)
        val shuffledQuestion = if ((0..10).random().rem(2) == 0) ReferenceUtils.concatReference(
            ReferenceUtils.shuffleList(listOfAttributes)
        ) else concatQuestion
//        set exercise question
        questionTitle.text = shuffledQuestion
//        set exercise title
        val referenceTitleTextView = binding.referenceTitle
        referenceTitleTextView.text = arguments?.getString("title")

        fun answerQuestion(selectedAnswer: Boolean) {
            if (selectedAnswer) {
                if (concatQuestion == shuffledQuestion) {
                    Toast.makeText(context, "Correcto! La referencia esta bien construida.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Respuesta incorrecta!", Toast.LENGTH_LONG).show()
                    questions.text = "Respuesta correcta: " + concatQuestion
                }
            } else if (!selectedAnswer) {
                if (concatQuestion != shuffledQuestion) {
                    Toast.makeText(context, "Respuesta correcta! La referencia tiene errores.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Respuesta incorrecta!", Toast.LENGTH_LONG).show()
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExerciseFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExerciseFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
