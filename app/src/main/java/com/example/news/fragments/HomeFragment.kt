package com.example.news.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.news.R
import com.example.news.adapter.ViewPagerAdapter
import com.example.news.viewModel.HomeViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val viewPagerAdapter by lazy {
        ViewPagerAdapter(this)
    }
    private var viewPager: ViewPager2? = null
    private var tabLayout: TabLayout? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.view_pager)
        tabLayout = view.findViewById(R.id.tab_layout)

        viewModel.switchStates.observe(viewLifecycleOwner) { states ->
            setupViewPager(states)
        }
    }

    private fun setupViewPager(states: List<String>) {
        viewPager?.adapter = viewPagerAdapter
        viewPagerAdapter.updateCategories(states)

        tabLayout?.let {tabLayout->
            viewPager?.let { pager2 ->
                TabLayoutMediator(tabLayout, pager2){ tab, position ->
                    tab.text = states[position]
                }.attach()
            }
        }
    }

}
