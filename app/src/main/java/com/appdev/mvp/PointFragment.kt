package com.appdev.mvp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class PointFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var scrollUpView: ConstraintLayout
    lateinit var nameView: TextView
    lateinit var dateView: TextView
    lateinit var registerBtn: ConstraintLayout
    lateinit var pointView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_point, container, false)
        initView(view)
        initListener()

        return view
    }

    private fun initView(view: View) {

        registerBtn = view.findViewById(R.id.po_register)
        recyclerView = view.findViewById(R.id.po_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        nameView = view.findViewById(R.id.po_name)
        dateView = view.findViewById(R.id.po_date)
        pointView = view.findViewById(R.id.po_point)


    }

    private fun initListener() {
        val sharedPreferences = activity!!.getSharedPreferences("info", Context.MODE_PRIVATE)
        val point = sharedPreferences.getInt("point", 0)
        pointView.text = point.toString()
        val email = sharedPreferences.getString("email", "null")
        InfoClient(activity!!, email).getInfo().getPoint()
            .enqueue(object : Callback<PointResult> {
                override fun onFailure(call: Call<PointResult>, t: Throwable) {
                    Toast.makeText(activity, "인터넷 연결을 확인해주세요", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<PointResult>,
                    response: Response<PointResult>
                ) {
                    if (response.isSuccessful) {
                        val adapter =
                            PointAdapter(
                                response.body()!!.data ?: listOf(),
                                activity as AppCompatActivity
                            )
                        recyclerView.adapter = adapter
                    }
                }
            })

        val name = sharedPreferences.getString("name", "null")
        nameView.text = name + "님의 보유 포인트는"
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (dy > 0) {
                            scrollUpView.animate().translationY(-1050F)
                        } else {
                            scrollUpView.animate().translationY(0F)
                        }
                    }
                })
            }
        })
        val instance = Calendar.getInstance()
        val year = instance.get(Calendar.YEAR).toString()
        val month = (instance.get(Calendar.MONTH) + 1).toString()
        val date = instance.getActualMaximum(Calendar.DAY_OF_MONTH).toString()
        dateView.text = "${year}.${month}.${date} 이후 소멸할 포인트는 "

        registerBtn.setOnClickListener {
            activity!!.viewpager2.currentItem = 0
        }
    }
}