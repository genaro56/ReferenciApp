package com.example.referenciapp.tabs.paperTab
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.referenciapp.R
import com.example.referenciapp.databinding.FragmentPaperTabBinding
import com.example.referenciapp.recycler.PrintExerciseListAdapter

class PaperTabFragment : Fragment() {

    lateinit var exerciseRecycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentPaperTabBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_paper_tab,
            container,
            false
        )

        val recyclerView = binding.paperRecyclerView
        val adapter = PrintExerciseListAdapter(requireNotNull(context))
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireNotNull(context))

        return binding.root
    }

}
