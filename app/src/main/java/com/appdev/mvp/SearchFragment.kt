package com.appdev.mvp

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SearchFragment() : BottomSheetDialogFragment() {
    lateinit var pointView: TextView
    lateinit var upView: ImageView
    lateinit var downView: ImageView
    lateinit var startDate: ConstraintLayout
    lateinit var endDate: ConstraintLayout
    lateinit var startYear: TextView
    lateinit var startMonth: TextView
    lateinit var startDay: TextView
    lateinit var endYear: TextView
    lateinit var endMonth: TextView
    lateinit var endDay: TextView


    private var point: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register2, container, false)

        initView(view)
        initListener()







        return view
    }


    private fun initView(view: View) {
        pointView = view.findViewById<TextView>(R.id.textView89)
        upView = view.findViewById<ImageView>(R.id.imageView7)
        downView = view.findViewById<ImageView>(R.id.imageView8)
        startDate = view.findViewById<ConstraintLayout>(R.id.constraintLayout76)
        endDate = view.findViewById<ConstraintLayout>(R.id.constraintLayout79)

        startYear= view.findViewById<TextView>(R.id.textView14)
        startMonth= view.findViewById<TextView>(R.id.textView38)
        startDay= view.findViewById<TextView>(R.id.textView48)
        endYear= view.findViewById<TextView>(R.id.textView79)
        endMonth= view.findViewById<TextView>(R.id.textView81)
        endDay= view.findViewById<TextView>(R.id.textView83)
    }

    private fun initListener() {

        startDate.setOnClickListener {

            val listener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                    Log.d("kkkk", "" + p1 + p2 + p3)
                    startYear.text = p1.toString()
                    startMonth.text = (p2+1).toString()
                    startDay.text = p3.toString()


                }
            }

            val dialog = DatePickerDialog(activity!!, listener, 2020, 11, 6);
            dialog.show();
        }

        endDate.setOnClickListener {
            val listener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                    endYear.text = p1.toString()
                    endMonth.text = (p2+1).toString()
                    endDay.text = p3.toString()
                }
            }
            val dialog = DatePickerDialog(activity!!, listener, 2020, 11, 6);
            dialog.show();
        }


        upView.setOnClickListener {
            point += 1
            pointView.text = point.toString()
        }

        downView.setOnClickListener {
            if (point > 0) {
                point -= 1
                pointView.text = point.toString()
            }
        }
    }
}