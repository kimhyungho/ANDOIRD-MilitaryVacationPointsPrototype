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

class PointFragment : Fragment() {

    private val dummyPointList: ArrayList<PointResult> = arrayListOf(
        PointResult(Date(), "유격왕", 3),
        PointResult(Date(), "태권도", 1)
    )


    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_point, container, false)
        initView(view)

        return view
    }

    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        val adapter = PointAdapter(dummyPointList, activity as AppCompatActivity)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

    private fun initListener() {


    }
}