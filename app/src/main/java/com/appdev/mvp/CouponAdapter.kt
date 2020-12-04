package com.appdev.mvp

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

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

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CouponViewHolder, position: Int) {
        val item = couponList[position]
        val mFormat = SimpleDateFormat("yyyy.MM.dd")
        holder.check.text = item.check
        holder.startDate.text = mFormat.format(item.start_date) + "~"
        holder.endDate.text = mFormat.format(item.end_date)
        holder.usePoint.text = item.use_point.toString() + " 포인트 사용"

        if (item.end_date > Date()) {
            holder.endDate.setTextColor(Color.parseColor("#4D916A"))
            holder.startDate.setTextColor(Color.parseColor("#4D916A"))
            holder.couponImage.setImageResource(R.drawable.ic_check)
            holder.usePoint.setTextColor(Color.parseColor("#92aa63"))
            holder.couponText.text = "조회"
            holder.arrow.setColorFilter(Color.parseColor("#4D916A"))
            holder.coupon_back.background.setTint(Color.parseColor("#4D916A"))


        }
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
    val couponImage = itemView.findViewById<ImageView>(R.id.coupon_image)
    val couponText = itemView.findViewById<TextView>(R.id.coupon_text)
    val coupon_back = itemView.findViewById<ConstraintLayout>(R.id.coupon_back)
    val arrow = itemView.findViewById<ImageView>(R.id.arrow_image)
}