package com.example.referenciapp.recycler


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.referenciapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ReferenceFieldsListAdapter(
    context: Context,
    private val attributes: MutableList<String>
    // val adapterOnClick: (Any) -> Unit
) : RecyclerView.Adapter<ReferenceFieldsListAdapter.FieldViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    inner class FieldViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: FieldViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}