package com.appdev.mvp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class PointAdapter(val pointList: List<Point>, val activity: AppCompatActivity) :
    RecyclerView.Adapter<PointViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointViewHolder {
        return PointViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_point, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pointList.size
    }

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        val item = pointList[position]
        val mFormat = SimpleDateFormat("yyyy-MM-dd")
        holder.date.text = mFormat.format(item.get_point_date)
        holder.title.text = item.point_reason
        holder.point.text = item.point.toString() + "포인트"

        if()

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}


class PointViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val date = itemView.findViewById<TextView>(R.id.date)
    val title = itemView.findViewById<TextView>(R.id.title)
    val point = itemView.findViewById<TextView>(R.id.point)
}