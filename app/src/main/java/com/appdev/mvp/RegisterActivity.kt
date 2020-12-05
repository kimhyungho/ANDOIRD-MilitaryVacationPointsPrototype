package com.appdev.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener
import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager
import com.applikeysolutions.cosmocalendar.view.CalendarView
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class RegisterActivity : AppCompatActivity() {

    lateinit var calendarView: CalendarView
    lateinit var pointView: TextView
    lateinit var upView: ImageView
    lateinit var downView: ImageView
    lateinit var endYearView: TextView
    lateinit var endMonthView: TextView
    lateinit var endDayView: TextView
    lateinit var startYearView: TextView
    lateinit var startMonthView: TextView
    lateinit var startDayView: TextView


    private var point = 0


    private var vacationDate: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()
        initListener()


    }

    private fun initView() {
        calendarView = register_calendar
        upView = up
        downView = down
        pointView = register_point

        endYearView = register_end_year
        endMonthView = register_end_month
        endDayView = register_end_day
        startYearView = register_start_year
        startMonthView = register_start_month
        startDayView = register_start_day
    }

    private fun initListener() {
        upView.setOnClickListener {
            point += 1
            pointView.text = point.toString()

        }

        downView.setOnClickListener {
            if(point > 0 ){
                point -= 1
                pointView.text = point.toString()
            }
        }



        calendarView.selectionManager = RangeSelectionManager(OnDaySelectedListener {
            Log.d(" CALENDAR ", "Selected Dates : " + calendarView.selectedDates.size)
            if (calendarView.selectedDates.size > 1) {
                val startDate = calendarView.selectedDates[0]
                val endDate = calendarView.selectedDates[calendarView.selectedDates.size - 1]

                endYearView.text = endDate.get(Calendar.YEAR).toString()
                endMonthView.text = endDate.get(Calendar.MONTH).toString()
                endDayView.text = endDate.get(Calendar.DAY_OF_MONTH).toString()
                startYearView.text = startDate.get(Calendar.YEAR).toString()
                startMonthView.text = startDate.get(Calendar.MONTH).toString()
                startDayView.text = startDate.get(Calendar.DAY_OF_MONTH).toString()
                return@OnDaySelectedListener
            }




        })
    }
}