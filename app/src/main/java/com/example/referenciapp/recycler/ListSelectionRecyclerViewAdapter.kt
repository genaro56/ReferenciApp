package com.example.referenciapp.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.referenciapp.R

class ListSelectionRecyclerViewAdapter():
    RecyclerView.Adapter<ListSelectionViewHolder>() {

    var listExercises = arrayOf("Exercise 1", "Exercise 2", "Exercise 3")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.list_selection_view_holder,
            parent,
            false
        )

        return ListSelectionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listExercises.size
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.exerciseTitle.text = listExercises[position]
//        holder.completionBar.background
    }
}
