package com.example.referenciapp.screens.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.referenciapp.R

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
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_exercise2, container, false)

        // Get the activity and widget
        val context = activity as AppCompatActivity

        // Replace fragment
        var questionIndex = 0

        val questionList: List<Question> = listOf<Question>(
            Question(
                "Mendoza Fillola, A., y Briz Villanueva, E. (2003). Didáctica de la lengua y la literatura para primaria. Madrid: Pearson Educación.",
                true
            ),
            Question(
                "Leal Isida, R., Barranco Ortega, P., y Flores Guajardo, M. (2016). Expresión verbal con finese específicos [Kindle versión 1.21.1]. Recuperado de Amazon.com",
                false
            ),
            Question(
                "Gurevich, Y. G. (2007). Fenómenos de transporte en semiconductores. México: Fondo de Cultura Económica.",
                true
            ),
            Question(
                "Fang, D., Qian, Y., y Hu, R. Q. Security for 5G Mobile. (2017). Wireless Networks. IEEE Access, 6, 4850-4874. doi: 10.1109/ACCESS.2017.2779146",
                false
            )
        )

        val questionTitle = v.findViewById<TextView>(R.id.questions)
        val questionIndexView = v.findViewById<TextView>(R.id.indexQuestion)

//        Buttons
        val trueButton: Button = v.findViewById(R.id.trueButton)
        val falseButton: Button = v.findViewById(R.id.falseButton)
        val nextButton: Button = v.findViewById(R.id.nextButton)
        val previousButton: Button = v.findViewById(R.id.previousButton)
//        set TextView data
        questionIndexView.text = (questionIndex + 1).toString()
        questionTitle.text = questionList[questionIndex].question

//        set next button to true
        nextButton.isEnabled = false
        previousButton.isEnabled = false
//        set exercise title
        val tv = v.findViewById<TextView>(R.id.reference_title)
        tv.text = arguments?.getString("title")

        fun answerQuestion(selectedAnswer: Boolean) {
            if (questionList[questionIndex].answer == selectedAnswer) {
                Toast.makeText(context, "Respuesta correcta", Toast.LENGTH_LONG).show()
                nextButton.isEnabled = true
            } else {
                Toast.makeText(context, "Respuesta incorrecta", Toast.LENGTH_LONG).show()
            }
        }

        fun changeQuestion(value: Number) {
            if (value == 1) {
                questionIndex++
                questionIndexView.text = (questionIndex + 1).toString()

            } else {
                questionIndex--
                questionIndexView.text = (questionIndex + 1).toString()
            }

            when {
                questionIndex < 0 -> {
                    questionIndex = 0
                    questionTitle.text = questionList[questionIndex].question
                }
                questionIndex < questionList.size -> {
                    questionTitle.text = questionList[questionIndex].question
                }
                questionIndex >= questionList.size -> {
                    questionIndex = questionList.size - 1
                    nextButton.isEnabled = false
                    previousButton.isEnabled = true
                }
            }
        }

        nextButton.setOnClickListener {
            changeQuestion(1)
        }
        previousButton.setOnClickListener {
            changeQuestion(-1)
        }

        falseButton.setOnClickListener {
            answerQuestion(false)
        }

        trueButton.setOnClickListener {
            answerQuestion(true)
        }
        return v
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
