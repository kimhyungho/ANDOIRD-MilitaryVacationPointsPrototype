package com.appdev.mvp

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.riontech.calendar.CustomCalendar
import java.util.*

class HomeFragment : Fragment() {

    lateinit var searchView: ImageView
    lateinit var todayBtn: TextView
    lateinit var searchBtn: ImageView
    lateinit var nameView: TextView
    lateinit var pointChart: PieChart
    lateinit var vacationChart: PieChart
    lateinit var remainDayChart: PieChart
    lateinit var calendarView : CustomCalendar


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
        nameView = view.findViewById(R.id.home_name)
        pointChart = view.findViewById(R.id.point_chart)
        vacationChart = view.findViewById(R.id.vacation_chart)
        remainDayChart = view.findViewById(R.id.remain_day_chart)
        calendarView = view.findViewById(R.id.home_calendar)



    }

    private fun initListener() {
        val pieDataSet = PieDataSet(arrayListOf(PieEntry(30F)), "")
        pieDataSet.setColors(Color.parseColor("#4D916A"), Color.parseColor("#C1C1C1"))
        val pieData = PieData(pieDataSet)
        pieData.setDrawValues(false)
        pointChart.setDrawMarkers(false); // To remove markers when click
        pointChart.description.isEnabled = false
        pointChart.setDrawSliceText(false)
        pointChart.legend.isEnabled = false
        pointChart.centerText = "45"
        pointChart.setCenterTextColor(Color.parseColor("#4D916A"))
        pointChart.data = pieData
        pointChart.invalidate()


        val pieDataSet2 = PieDataSet(arrayListOf(PieEntry(12F), PieEntry(6F)), "")
        pieDataSet2.setColors(Color.parseColor("#4D916A"), Color.parseColor("#C1C1C1"))
        val pieData2 = PieData(pieDataSet2)
        pieData2.setDrawValues(false)
        vacationChart.setDrawMarkers(false); // To remove markers when click
        vacationChart.description.isEnabled = false
        vacationChart.setDrawSliceText(false)
        vacationChart.legend.isEnabled = false
        vacationChart.centerText = "12"
        vacationChart.setCenterTextColor(Color.parseColor("#4D916A"))
        vacationChart.data = pieData2
        vacationChart.invalidate()


        val pieDataSet3 = PieDataSet(arrayListOf(PieEntry(509F), PieEntry(31F)), "")
        pieDataSet3.setColors(Color.parseColor("#4D916A"), Color.parseColor("#C1C1C1"))
        val pieData3 = PieData(pieDataSet3)
        pieData3.setDrawValues(false)
        remainDayChart.setDrawMarkers(false); // To remove markers when click
        remainDayChart.description.isEnabled = false
        remainDayChart.setDrawSliceText(false)
        remainDayChart.legend.isEnabled = false
        remainDayChart.centerText = "509"
        remainDayChart.centerCircleBox
        remainDayChart.setCenterTextColor(Color.parseColor("#4D916A"))
        remainDayChart.data = pieData3
        remainDayChart.invalidate()






        val sharedPreferences = activity!!.getSharedPreferences("info", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "null")

        nameView.text = "${name}님,"


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