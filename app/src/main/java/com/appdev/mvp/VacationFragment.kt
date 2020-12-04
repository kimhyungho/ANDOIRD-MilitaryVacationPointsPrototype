package com.appdev.mvp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_vacation.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class VacationFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var dateView: TextView
    lateinit var nameView: TextView
    lateinit var remainDay: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_vacation, container, false)
        initView(view)
        initListener()
        return view
    }

    private fun initView(view: View) {

        recyclerView = view.findViewById(R.id.va_recyclerView)
        dateView = view.findViewById(R.id.va_date)
        nameView = view.findViewById(R.id.va_name)
        remainDay = view.findViewById(R.id.va_day)
    }

    private fun initListener() {
        val sharedPreference = activity!!.getSharedPreferences("info", Context.MODE_PRIVATE)
        val instance = Calendar.getInstance()
        val year = instance.get(Calendar.YEAR).toString()
        val month = (instance.get(Calendar.MONTH) + 1).toString()
        val date = instance.getActualMaximum(Calendar.DAY_OF_MONTH).toString()
        val day = sharedPreference.getInt("remained_vacation", 0)
        val name = sharedPreference.getString("name", "null")
        nameView.text = "${name}님의 보유 휴가는"
        remainDay.text = day.toString()
        dateView.text = "${year}.${month}.${date} 이후 소멸할 휴가는 "


        val email = sharedPreference.getString("email", "null")
        InfoClient(activity!!, email).getInfo().getVacation()
            .enqueue(object : Callback<VacationResult> {
                override fun onFailure(call: Call<VacationResult>, t: Throwable) {
                    Toast.makeText(activity, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<VacationResult>,
                    response: Response<VacationResult>
                ) {
                    if (response.isSuccessful) {
                        val adapter = VacationAdapter(
                            response.body()!!.data ?: listOf(),
                            activity as AppCompatActivity
                        )
                        recyclerView.layoutManager = LinearLayoutManager(activity)
                        recyclerView.adapter = adapter
                    }
                }
            })

    }
}