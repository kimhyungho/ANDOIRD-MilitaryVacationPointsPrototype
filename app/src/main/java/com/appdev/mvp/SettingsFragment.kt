package com.appdev.mvp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.w3c.dom.Text

class SettingsFragment : Fragment() {
    lateinit var nameView: TextView
    lateinit var departmentView: TextView
    lateinit var remainDayView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        initView(view)
        initListener()

        return view
    }

    private fun initView(view: View) {
        nameView = view.findViewById(R.id.st_name)
        departmentView = view.findViewById(R.id.st_department)
        remainDayView = view.findViewById(R.id.st_remain_day)
    }

    private fun initListener() {
        val sharedPreferences = activity!!.getSharedPreferences("info", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "null")
        val department = sharedPreferences.getString("position", "null")
        val remainDay = sharedPreferences.getInt("remained_day", 0)
        nameView.text = name
        departmentView.text = department
        remainDayView.text = remainDay.toString() + "일"
    }


}