package com.example.referenciapp.recycler

import com.example.referenciapp.database.DigitalExercises
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.referenciapp.R
import kotlinx.android.synthetic.main.list_selection_view_holder.view.*

class DigitalExerciseListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<DigitalExerciseListAdapter.ExerciseViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var exercises = emptyList<DigitalExercises>() // cached copy of print exercises

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseLabel = itemView.exerciseLabel as TextView
        val exerciseTitle = itemView.exerciseTitle as TextView
        val completionBar = itemView.completionBar as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val itemView = inflater.inflate(R.layout.list_selection_view_holder, parent, false)
        return ExerciseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val current = exercises[position]
        holder.exerciseLabel.text = "Ejercicio ${position}:"
        holder.exerciseTitle.text = current.description

        if(current.completed)
            holder.completionBar.setBackgroundColor(Color.GREEN)
    }

    internal fun setExercises(exercises: List<DigitalExercises>) {
        this.exercises = exercises
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = exercises.size
}
