package com.example.referenciapp.screens.referenceMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.referenciapp.tabs.digitalTab.DigitalTabFragment
import com.example.referenciapp.tabs.paperTab.PaperTabFragment
import com.example.referenciapp.R
import com.example.referenciapp.tabs.ViewPagerAdapter
import com.example.referenciapp.databinding.FragmentReferenceMenuBinding

class ReferenceMenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentReferenceMenuBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_reference_menu,
            container,
            false
        )

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager
        tabLayout.setupWithViewPager(viewPager)
        val supportFragmentManager = childFragmentManager

        val adapter =
            ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(PaperTabFragment(), "Impresas")
        adapter.addFragment(DigitalTabFragment(), "Digitales")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_description_black_24dp)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_desktop_mac_black_24dp)

        val supportActionBar = (activity as AppCompatActivity).supportActionBar
        supportActionBar?.title = "Lista de ejercicios"
        supportActionBar?.subtitle = ""

        return binding.root
    }
}
