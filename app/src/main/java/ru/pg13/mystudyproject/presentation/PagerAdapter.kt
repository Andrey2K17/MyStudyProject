package ru.pg13.mystudyproject.presentation

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int) = if (position == 0)
        JokesFragment()
    else
        QuotesFragment()
}