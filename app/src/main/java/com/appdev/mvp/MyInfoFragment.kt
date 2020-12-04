package com.appdev.mvp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MyInfoFragment : Fragment() {

    lateinit var recyclerView: RecyclerView   // 휴가증 recycler view

    private val couponList = mutableListOf<Coupon>(
        Coupon(true, "2020.10.01", "2020.10.01", 12),
        Coupon(true, "2020.10.01", "2020.10.01", 12),
        Coupon(true, "2020.10.01", "2020.10.01", 12),
        Coupon(true, "2020.10.01", "2020.10.01", 12),
        Coupon(true, "2020.10.01", "2020.10.01", 12),
        Coupon(true, "2020.10.01", "2020.10.01", 12)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_myinfo, container, false)
        initView(view)       // view 등록
        initListener()       // listener 등록
        return view         // 뷰 화면에 표시
    }

    // view init
    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.coupon_recyclerview)


    }

    // listener init
    private fun initListener() {

        val sharedPreferences = activity!!.getSharedPreferences("info", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", null)
        if (email != null) {
            InfoClient(activity!!, email).getInfo().getCoupon()
                .enqueue(object : Callback<CouponResult> {
                    override fun onFailure(call: Call<CouponResult>, t: Throwable) {
                        Toast.makeText(activity, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<CouponResult>,
                        response: Response<CouponResult>
                    ) {
                        if (response.isSuccessful) {
                            recyclerView.layoutManager = LinearLayoutManager(activity)
                            recyclerView.adapter =
                                CouponAdapter(response.body()!!.data, activity as AppCompatActivity)
                        }
                    }
                })
        }
    }
}