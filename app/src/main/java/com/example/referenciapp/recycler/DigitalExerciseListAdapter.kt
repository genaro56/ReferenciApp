package com.example.referenciapp.recycler

import com.example.referenciapp.database.DigitalExercises
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.referenciapp.R
import com.example.referenciapp.ReferenceMenuViewModel
import kotlinx.android.synthetic.main.list_selection_view_holder.view.*

class DigitalExerciseListAdapter(
    context: Context,
    viewModel: ReferenceMenuViewModel
) : RecyclerView.Adapter<DigitalExerciseListAdapter.ExerciseViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var exercises = emptyList<DigitalExercises>() // cached copy of print exercises
    val vm = viewModel

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseLabel = itemView.exerciseLabel as TextView
        val exerciseTitle = itemView.exerciseTitle as TextView
        val completionBar = itemView.completionBar as ImageView

        init {
            itemView.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                    R.id.action_global_exerciseFragment
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val itemView = inflater.inflate(R.layout.list_selection_view_holder, parent, false)
        return ExerciseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val current = exercises[position]
        holder.exerciseLabel.text = "Ejercicio ${position + 1}"
        holder.exerciseTitle.text = current.description

        if(current.completed)
            holder.completionBar.setBackgroundColor(Color.GREEN)

        holder.itemView.setOnClickListener{view ->
            vm.setSelectedId(position)
            vm.setResourceType(1)
            view.findNavController().navigate(R.id.action_global_exerciseFragment)
        }
    }

    internal fun setExercises(exercises: List<DigitalExercises>) {
        this.exercises = exercises
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = exercises.size
}
