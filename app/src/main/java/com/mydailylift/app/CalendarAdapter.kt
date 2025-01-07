package com.mydailylift.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalendarAdapter(
    private val onDayClick: (String, Boolean) -> Unit
) : RecyclerView.Adapter<CalendarAdapter.DayViewHolder>() {

    private val days = generateCalendarDays()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = days[position]
        holder.bind(day, position < todayPosition(), onDayClick)
    }

    override fun getItemCount(): Int = days.size

    private fun todayPosition(): Int {
        // TODO: Calculate the current day position in the calendar
        return 15 // Placeholder value
    }

    private fun generateCalendarDays(): List<String> {
        // TODO: Generate a list of calendar days for the current month
        return (1..31).map { it.toString() }
    }

    class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dayTextView: TextView = itemView.findViewById(R.id.calendar_day_text)

        fun bind(day: String, isPastDay: Boolean, onDayClick: (String, Boolean) -> Unit) {
            dayTextView.text = day
            itemView.setOnClickListener { onDayClick(day, isPastDay) }
        }
    }
}
