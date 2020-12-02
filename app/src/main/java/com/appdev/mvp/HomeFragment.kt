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

    lateinit var calendarView: MaterialCalendarView
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
        calendarView = view.findViewById<MaterialCalendarView>(R.id.calendar)
        searchView = view.findViewById<ImageView>(R.id.home_search)
        todayBtn = view.findViewById(R.id.home_today)
        searchBtn = view.findViewById(R.id.home_search)

        calendarView


    }

    private fun initListener() {
        todayBtn.setOnClickListener {
            calendarView.setCurrentDate(calendarView.currentDate, true)
        }



//        todayBtn.setOnClickListener {
//            val mFormat = SimpleDateFormat(DATE_FORMAT)
//            val today = mFormat.format(Date())
//            calendarView.selectedDate = getCalendarDay(today)
//        }


        calendarView.showOtherDates = MaterialCalendarView.SHOW_ALL
        setEvent(pinkDateList, green)
        setEvent(grayDateList, gray)

        searchView.setOnClickListener {
            val bottomSheet = SearchFragment()
            bottomSheet.show(activity!!.supportFragmentManager, bottomSheet.tag)
        }

    }

    private fun setEvent(dateList: MutableList<String>, color: Int) {
        var calendarDayList: MutableList<CalendarDay> = ArrayList()
        for (string in dateList) {
            var calendar: CalendarDay? = getCalendarDay(string)
            if (calendar != null) {
                calendarDayList.add(calendar)
            }
        }

        val datesLeft: MutableList<CalendarDay> = ArrayList()
        val datesCenter: MutableList<CalendarDay> = ArrayList()
        val datesRight: MutableList<CalendarDay> = ArrayList()
        val datesIndependent: MutableList<CalendarDay> = ArrayList()

        for (calendarDay in calendarDayList) {
            var right = false
            var left = false

            for (day1 in calendarDayList) {
                if (calendarDay == CalendarDay.from(day1.year, day1.month, day1.day + 1)) {
                    left = true
                }

                if (day1 == CalendarDay.from(
                        calendarDay.year,
                        calendarDay.month,
                        calendarDay.day + 1
                    )
                ) {
                    right = true
                }
            }
            if (left && right) {
                datesCenter.add(calendarDay)
            } else if (left) {
                datesLeft.add(calendarDay)
            } else if (right) {
                datesRight.add(calendarDay)
            } else {
                datesIndependent.add(calendarDay)
            }
        }

        if (color == green) {
            setDecor(datesCenter, R.drawable.m_center);
            setDecor(datesLeft, R.drawable.m_left);
            setDecor(datesRight, R.drawable.m_right);
            setDecor(datesIndependent, R.drawable.m_independent);
        } else {
            setDecor(datesCenter, R.drawable.g_center)
            setDecor(datesLeft, R.drawable.g_left)
            setDecor(datesRight, R.drawable.g_right)
            setDecor(datesIndependent, R.drawable.g_independent)

        }
    }

    private fun setDecor(calendarDayList: List<CalendarDay>, drawable: Int) {
        calendarView.addDecorator(EventDecorator(activity!!, drawable, calendarDayList))
    }

    private fun getCalendarDay(date: String): CalendarDay? {
        val sdf = SimpleDateFormat(DATE_FORMAT)
        try {
            val input = sdf.parse(date)
            val cal = Calendar.getInstance()
            cal.time = input
            return CalendarDay.from(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.DAY_OF_MONTH)
            )
        } catch (e: NullPointerException) {
            return null
        } catch (e: ParseException) {
            return null
        }
    }


}