package com.appdev.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager2.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(tablayout, viewpager2) { tab, position ->
            val tabLayoutTextArray = arrayOf("홈", "내역 확인", "내 정보", "설정")
            val tabLayoutIconArray = arrayOf(
                R.drawable.ic_baseline_calendar_today_24,
                R.drawable.ic_baseline_assignment_24,
                R.drawable.ic_baseline_person_outline_24,
                R.drawable.ic_baseline_dehaze_24
            )

            tab.text = tabLayoutTextArray[position]
            tab.setIcon(tabLayoutIconArray[position])
        }.attach()

    }


}