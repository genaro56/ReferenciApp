package com.app.referenciapp.tabs.paperTab
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.referenciapp.R
import com.app.referenciapp.ReferenceMenuViewModel
import com.app.referenciapp.databinding.FragmentPaperTabBinding
import com.app.referenciapp.recycler.PrintExerciseListAdapter

class PaperTabFragment : Fragment() {

    private lateinit var referenceViewModel: ReferenceMenuViewModel
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

        // We use requireActivity to use the /shared/ ViewModel
        referenceViewModel = ViewModelProvider(requireActivity()).get(ReferenceMenuViewModel::class.java)

        val recyclerView = binding.paperRecyclerView
        val adapter = PrintExerciseListAdapter(requireNotNull(context), referenceViewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireNotNull(context))

        referenceViewModel.allPrintExercises.observe(viewLifecycleOwner, Observer { exercises ->
            exercises?.let { adapter.setExercises(it)}
            referenceViewModel.countCompletePrint()
        })

        referenceViewModel.numCompletePrint.observe(viewLifecycleOwner, Observer { num ->
            binding.progressLabel.text = "Progreso: ${num} / ${adapter.itemCount}"
        })

        return binding.root
    }

}
