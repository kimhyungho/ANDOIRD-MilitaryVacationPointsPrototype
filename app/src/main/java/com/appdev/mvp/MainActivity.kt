package com.appdev.mvp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(
            this@MainActivity,
            OnSuccessListener<InstanceIdResult> { instanceIdResult ->
                val token = instanceIdResult.token
                Log.i("FCM Token", token)
            })


        viewpager2.adapter = ViewPagerAdapter(this)

        val intent = intent
        if (intent != null) {//푸시알림을 선택해서 실행한것이 아닌경우 예외처리
            val notificationData = intent.getStringExtra("test");
            if (notificationData != null)
                Log.d("FCM", notificationData);
        }

        TabLayoutMediator(tablayout, viewpager2) { tab, position ->
            val tabLayoutTextArray = arrayOf("홈", "나의 휴가", "승인 내역", "설정")
            val tabLayoutIconArray = arrayOf(
                R.drawable.ic_home,
                R.drawable.ic_info,
                R.drawable.ic_check,
                R.drawable.ic_setting
            )

            tab.text = tabLayoutTextArray[position]
            tab.setIcon(tabLayoutIconArray[position])
        }.attach()

    }


}