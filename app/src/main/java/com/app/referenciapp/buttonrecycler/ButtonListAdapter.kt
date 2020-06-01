package com.app.referenciapp.buttonrecycler


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.referenciapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ButtonListAdapter(
    context: Context,
    private val attributes: MutableList<String>
    // val adapterOnClick: (Any) -> Unit
) : RecyclerView.Adapter<ButtonListAdapter.ButtonListViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    // val attributes = listOf<String>("titulo", "año", "editor", "bleh")
    companion object {
        // Default card elevation.
        const val CARD_ELEVATION_DEFAULT_DP: Float = 2f

        // Card elevation once the dragging has started.
        const val CARD_ELEVATION_DRAG_START_DP: Float = 8f

        // Card elevation once the color is dragged over one of the areas.
        const val CARD_ELEVATION_DRAG_ENTER_DP: Float = 16f
    }

    override fun onBindViewHolder(holder: ButtonListViewHolder, position: Int) {

        val current = attributes[position]
        holder.attributeLabel.text = attributes[position]
//        holder.setDragListener(attributes[position])

    }

    inner class ButtonListViewHolder : RecyclerView.ViewHolder {
        var attributeButton: FloatingActionButton
        var attributeLabel: TextView

        constructor(itemView: View) : super(itemView) {
            attributeButton = itemView.findViewById(R.id.fabButton)
            attributeLabel = itemView.findViewById(R.id.attributeLabel)
        }

//        //set our click method here
//        fun setDragListener(item: Any) {
//            attributeButton.setOnClickListener {
//                adapterOnClick(item)
//            }
//        }

        init {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonListViewHolder {
        val itemView = inflater.inflate(R.layout.button_list_view_holder, parent, false)
        return ButtonListViewHolder(itemView)
    }

    override fun getItemCount(): Int = attributes.size
}