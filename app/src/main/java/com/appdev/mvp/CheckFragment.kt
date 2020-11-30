package com.appdev.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_check.viewpager2
import com.google.android.material.tabs.TabLayoutMediator as TabLayoutMediator1

class CheckFragment : Fragment() {

    lateinit var tabLayOut: TabLayout
    lateinit var viewPager: ViewPager2
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_check, container, false)

        initView(view)

        viewPager.adapter = Tab2PagerAdapter(activity!!)
        TabLayoutMediator1(tabLayOut, viewPager) { tab, position ->
            val tabLayoutTextArray = arrayOf("포인트", "휴가")
            tab.text = tabLayoutTextArray[position]
        }.attach()

        return view

    }

    private fun initView(view: View) {
        tabLayOut = view.findViewById(R.id.tablayout)
        viewPager = view.findViewById(R.id.viewpager2)
    }
}