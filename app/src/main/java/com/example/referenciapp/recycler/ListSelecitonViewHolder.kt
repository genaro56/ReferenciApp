package com.example.referenciapp.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_selection_view_holder.view.*

class ListSelectionViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    val exerciseTitle = itemView.exerciseTitle as TextView
    val completionBar = itemView.completionBar as ImageView

//    init {
//        itemView.setOnClickListener {
//            val browserIntent: Intent = Intent(ACTION_VIEW, Uri.parse(imageUrl))
//            itemView.context.startActivity(browserIntent)
//        }
//    }
}
