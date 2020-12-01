package com.appdev.mvp

import android.content.Context
import android.os.Bundle
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
import kotlin.collections.ArrayList

class VacationFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_vacation, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        val sharedPreference = activity!!.getSharedPreferences("info", Context.MODE_PRIVATE)
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
                        recyclerView = view.findViewById(R.id.va_recyclerView)
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