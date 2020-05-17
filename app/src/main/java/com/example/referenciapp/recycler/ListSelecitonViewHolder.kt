package com.example.referenciapp.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
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
            itemView.findNavController().navigate(
                R.id.action_global_exerciseFragment
            )
        }
    }
}
