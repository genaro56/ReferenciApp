package com.example.referenciapp.recycler

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.referenciapp.R
import com.example.referenciapp.database.PrintExercises
import kotlinx.android.synthetic.main.list_selection_view_holder.view.*

class PrintExerciseListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<PrintExerciseListAdapter.ExerciseViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var exercises = emptyList<PrintExercises>() // cached copy of print exercises

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

    override fun getItemCount(): Int = exercises.size
}