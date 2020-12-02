package com.appdev.mvp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class PointFragment : Fragment() {

    val listPoint =

        listOf(
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1), Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1), Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1), Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1), Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1), Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1), Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1), Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1), Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1), Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1), Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1), Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1),
            Point(1, "d", Date(), "ddd", 1)
        )


    lateinit var recyclerView: RecyclerView

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

        recyclerView = view.findViewById(R.id.po_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)


        val sharedPreferences = activity!!.getSharedPreferences("info", Context.MODE_PRIVATE)
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
                                listPoint,
//                                response.body()!!.data ?: listOf(),
                                activity as AppCompatActivity
                            )
                        recyclerView.adapter = adapter
                    }
                }
            })


    }

    private fun initListener() {

        val mScrollPosition =
            (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (dy > 0) {
                            recyclerView.animate().translationY(-300F)
                        } else {
                            recyclerView.animate().translationY(0F)
                        }
                    }
                })
            }
        })
    }
}