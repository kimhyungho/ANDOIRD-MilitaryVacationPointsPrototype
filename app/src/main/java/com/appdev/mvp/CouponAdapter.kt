package com.appdev.mvp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class CouponAdapter(val couponList: List<Coupon>, val activity: AppCompatActivity) :
    RecyclerView.Adapter<CouponViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CouponViewHolder {
        return CouponViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_coupon, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return couponList.size
    }

    override fun onBindViewHolder(holder: CouponViewHolder, position: Int) {
        val item = couponList[position]
        val mFormat = SimpleDateFormat("yyyy.MM.dd")
        holder.check.text = if(item.check == true) "승인" else "기각"
        holder.startDate.text = item.start_date
        holder.endDate.text = item.end_date
        holder.usePoint.text = item.use_point.toString()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}


class CouponViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val check = itemView.findViewById<TextView>(R.id.check)
    val startDate = itemView.findViewById<TextView>(R.id.startDate)
    val endDate = itemView.findViewById<TextView>(R.id.endDate)
    val usePoint = itemView.findViewById<TextView>(R.id.usePoint)
}