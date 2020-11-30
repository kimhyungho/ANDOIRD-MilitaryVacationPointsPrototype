package com.appdev.mvp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class VacationAdapter(val vacationList: List<VacationResult>, val activity: AppCompatActivity) :
    RecyclerView.Adapter<VacationViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacationViewHolder {
        return VacationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_vacation, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return vacationList.size
    }

    override fun onBindViewHolder(holder: VacationViewHolder, position: Int) {
        val item = vacationList[position]
        val mFormat = SimpleDateFormat("yyyy-MM-dd")
        holder.date.text = mFormat.format(item.date)
        holder.title.text = item.title
        holder.vacation.text = item.vacation.toString() + "일"
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}


class VacationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val date = itemView.findViewById<TextView>(R.id.date)
    val title = itemView.findViewById<TextView>(R.id.title)
    val vacation = itemView.findViewById<TextView>(R.id.vacation)
}