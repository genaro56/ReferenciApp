package com.example.referenciapp.tabs.paperTab
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.referenciapp.R
import com.example.referenciapp.databinding.FragmentPaperTabBinding

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

//        // RecyclerView setup
//        exerciseRecycler = binding.paperRecyclerView
//        exerciseRecycler.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = ListSelectionRecyclerViewAdapter()
//        }

        return binding.root
    }

}
