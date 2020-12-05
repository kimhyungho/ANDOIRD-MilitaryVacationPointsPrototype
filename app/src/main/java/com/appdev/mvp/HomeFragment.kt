package com.appdev.mvp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.lang.NullPointerException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    lateinit var searchView: ImageView
    lateinit var todayBtn: TextView
    lateinit var searchBtn: ImageView

    val pinkDateList: MutableList<String> = Arrays.asList(
        "2020-11-01",
        "2020-11-03", "2020-11-04", "2020-11-05", "2020-11-06"
    )
    val grayDateList: MutableList<String> = Arrays.asList(
        "2020-11-09", "2020-11-10", "2020-11-11",
        "2020-11-24", "2020-11-25", "2020-11-26", "2020-11-27", "2020-11-28", "2020-11-29"
    )

    val DATE_FORMAT = "yyyy-MM-dd"

    var green = 0
    var gray = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        initView(view)
        initListener()

        return view
    }

    private fun initView(view: View) {
//        calendarView = view.findViewById<MaterialCalendarView>(R.id.calendar)
        searchView = view.findViewById<ImageView>(R.id.home_search)
        todayBtn = view.findViewById(R.id.home_today)
        searchBtn = view.findViewById(R.id.home_search)



    }

    private fun initListener() {




//        todayBtn.setOnClickListener {
//            val mFormat = SimpleDateFormat(DATE_FORMAT)
//            val today = mFormat.format(Date())
//            calendarView.selectedDate = getCalendarDay(today)
//        }



        searchView.setOnClickListener {
            val bottomSheet = SearchFragment()
            bottomSheet.show(activity!!.supportFragmentManager, bottomSheet.tag)
        }

    }



}