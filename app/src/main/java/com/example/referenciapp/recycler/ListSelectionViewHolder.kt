package com.example.referenciapp.recycler

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.referenciapp.R
import kotlinx.android.synthetic.main.list_selection_view_holder.view.*


class ListSelectionViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    val exerciseTitle = itemView.exerciseTitle as TextView
    val completionBar = itemView.completionBar as ImageView

    init {
        itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("title", exerciseTitle.text.toString())
            if (exerciseTitle.text == "Exercise 2") {
                itemView.findNavController().navigate(R.id.action_global_exerciseFragment2, bundle)
            } else {
                itemView.findNavController().navigate(R.id.action_global_exerciseFragment, bundle)
            }
        }
    }
}
