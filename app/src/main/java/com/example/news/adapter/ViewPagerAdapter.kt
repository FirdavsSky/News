package com.example.news.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.news.R
import com.example.news.fragments.ViewPagerFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val categories = mutableListOf<String>()

    fun updateCategories(categories: List<String>){
        this.categories.apply {
            if (categories.isEmpty()) return
            clear()
            addAll(categories)
            notifyItemRangeChanged(0,categories.lastIndex)
        }
    }

    override fun getItemCount(): Int = categories.size

    override fun createFragment(position: Int): Fragment {
        return ViewPagerFragment.newInstance(categories[position])
    }
}