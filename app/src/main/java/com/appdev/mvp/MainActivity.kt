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
                R.drawable.ic_home,
                R.drawable.ic_check,
                R.drawable.ic_info,
                R.drawable.ic_setting
            )

            tab.text = tabLayoutTextArray[position]
            tab.setIcon(tabLayoutIconArray[position])
        }.attach()

    }


}