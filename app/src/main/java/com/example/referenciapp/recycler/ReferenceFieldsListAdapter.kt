package com.example.referenciapp.recycler


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.referenciapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.reference_fields_view_holder.view.*

class ReferenceFieldsListAdapter(
    context: Context
) : RecyclerView.Adapter<ReferenceFieldsListAdapter.FieldViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var attributes = emptyList<String>()

    inner class FieldViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val fieldLabel = itemView.fieldLabel as TextView
        val fieldValueTextView = itemView.fieldTextView as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldViewHolder {
        val itemView = inflater.inflate(R.layout.reference_fields_view_holder, parent, false)
        return FieldViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FieldViewHolder, position: Int) {
        val current = attributes[position]

        holder.fieldValueTextView.text = current
    }

    internal fun setAttributes(attrs: List<String>) {
        this.attributes = attrs
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return attributes.size
    }

}