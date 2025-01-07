package com.mydailylift.app.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mydailylift.app.R
import com.mydailylift.app.data.Routine

class RoutinesAdapter : RecyclerView.Adapter<RoutinesAdapter.RoutineViewHolder>() {

    private val routines = mutableListOf<Routine>()

    fun submitList(newRoutines: List<Routine>) {
        routines.clear()
        routines.addAll(newRoutines)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_routine, parent, false)
        return RoutineViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoutineViewHolder, position: Int) {
        holder.bind(routines[position])
    }

    override fun getItemCount(): Int = routines.size

    class RoutineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val routineName: TextView = itemView.findViewById(R.id.routine_name)
        private val routineDetails: TextView = itemView.findViewById(R.id.routine_details)

        fun bind(routine: Routine) {
            routineName.text = routine.name
            routineDetails.text = "${routine.exercises.size} exercises"
        }
    }
}
