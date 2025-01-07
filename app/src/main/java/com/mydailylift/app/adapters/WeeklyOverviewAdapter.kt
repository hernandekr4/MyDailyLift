package com.mydailylift.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mydailylift.app.R

class WeeklyOverviewAdapter(
    private val days: List<String>,
    private val onDayClick: (String) -> Unit
) : RecyclerView.Adapter<WeeklyOverviewAdapter.DayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_week_day, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = days[position]
        holder.bind(day, onDayClick)
    }

    override fun getItemCount(): Int = days.size

    class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dayTextView: TextView = itemView.findViewById(R.id.week_day_text)

        fun bind(day: String, onDayClick: (String) -> Unit) {
            dayTextView.text = day
            itemView.setOnClickListener { onDayClick(day) }
        }
    }
}
