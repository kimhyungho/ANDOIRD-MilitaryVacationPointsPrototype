package com.appdev.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class VacationFragment : Fragment() {

    private val dummyVacationList: ArrayList<VacationResult> = arrayListOf(
        VacationResult(Date(), "유격왕", 3),
        VacationResult(Date(), "태권도", 1),
        VacationResult(Date(), "체육대회", 2)
    )

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
        recyclerView = view.findViewById(R.id.va_recyclerView)
        val adapter = VacationAdapter(dummyVacationList, activity as AppCompatActivity)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }
}