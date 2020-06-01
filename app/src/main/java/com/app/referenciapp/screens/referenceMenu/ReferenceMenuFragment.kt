package com.app.referenciapp.screens.referenceMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.referenciapp.tabs.digitalTab.DigitalTabFragment
import com.app.referenciapp.tabs.paperTab.PaperTabFragment
import com.app.referenciapp.R
import com.app.referenciapp.tabs.ViewPagerAdapter
import com.app.referenciapp.databinding.FragmentReferenceMenuBinding

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
        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_description_black_24dp)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_desktop_mac_black_24dp)

        return binding.root
    }
}
