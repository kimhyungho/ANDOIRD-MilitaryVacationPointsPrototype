package com.appdev.mvp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener
import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager
import com.applikeysolutions.cosmocalendar.view.CalendarView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RegisterActivity : AppCompatActivity() {

    lateinit var calendarView: CalendarView
    lateinit var pointView: TextView
    lateinit var upView: ImageView
    lateinit var downView: ImageView
    lateinit var normalVacationView: TextView
    lateinit var additionalVacationView: TextView
    lateinit var registerBtn: TextView


    private var point = 0


    private var vacationDate: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()
        initListener()


    }

    private fun initView() {
//        calendarView = register_calendar
//        upView = up
//        downView = down
//        pointView = register_point
//
//        normalVacationView = normal_vacation
//        additionalVacationView = addtional_vacation
//        registerBtn = register_button
    }

    private fun initListener() {

        val sharedPreferences = getSharedPreferences("info", Context.MODE_PRIVATE)
        val fcmToken = sharedPreferences.getString("FCM", null)
        Log.d("kkkk", fcmToken ?: "null")

        registerBtn.setOnClickListener {
            if (fcmToken != null) {
                PushClient(this).push().sendRequest(ConfirmRequest("3", "3", fcmToken!!)).enqueue(
                    object : Callback<ConfirmResponse> {
                        override fun onFailure(call: Call<ConfirmResponse>, t: Throwable) {
                            Toast.makeText(
                                this@RegisterActivity,
                                "인터넷 연결을 확인해 주새요",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onResponse(
                            call: Call<ConfirmResponse>,
                            response: Response<ConfirmResponse>
                        ) {
                            if (response.isSuccessful) {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "휴가를 신청했습니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {

                            }
                        }
                    }
                )
                finish()
            } else {
                Toast.makeText(this, "FCM 토큰이 없습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        normalVacationView.setOnClickListener {
            val items = resources.getStringArray(R.array.normal_vacation)
            MaterialAlertDialogBuilder(this)
                .setItems(items) { dialog, whcih ->
                    normalVacationView.text = items[whcih]
                }
                .show()
        }

        additionalVacationView.setOnClickListener {
            val items = resources.getStringArray(R.array.additional_vacation)
            MaterialAlertDialogBuilder(this)
                .setItems(items) { dialog, whcih ->
                    additionalVacationView.text = items[whcih]

                }
                .show()
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

        calendarView.selectionManager = RangeSelectionManager(OnDaySelectedListener {
            Log.d(" CALENDAR ", "Selected Dates : " + calendarView.selectedDates.size)
            if (calendarView.selectedDates.size > 1) {
                val startDate = calendarView.selectedDates[0]
                val endDate = calendarView.selectedDates[calendarView.selectedDates.size - 1]

                return@OnDaySelectedListener
            }


        })
    }
}