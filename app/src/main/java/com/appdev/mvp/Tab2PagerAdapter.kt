package com.appdev.mvp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class Tab2PagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PointFragment()
            1 -> VacationFragment()
            else -> HomeFragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}