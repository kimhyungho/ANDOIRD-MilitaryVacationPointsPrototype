package com.appdev.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_home.*

class SearchFragment() : BottomSheetDialogFragment() {
    lateinit var pointView: TextView
    lateinit var upView: ImageView
    lateinit var downView: ImageView

    private var point: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        initView(view)
        initListener()

        return view
    }

    private fun initView(view: View) {
        pointView = view.findViewById<TextView>(R.id.point)
        upView = view.findViewById<ImageView>(R.id.up)
        downView = view.findViewById<ImageView>(R.id.down)
    }

    private fun initListener() {
        upView.setOnClickListener {
            point += 1
            pointView.text = point.toString()
        }

        downView.setOnClickListener {
            if(point > 0) {
                point -= 1
                pointView.text = point.toString()
            }
        }
    }
}