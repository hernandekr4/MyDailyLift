package com.mydailylift.app.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mydailylift.app.R
import com.mydailylift.app.data.Routine

class RoutinesAdapter(
    private val routines: MutableList<Routine> = mutableListOf(),
    private val onActionClick: (Routine, String) -> Unit
) : RecyclerView.Adapter<RoutinesAdapter.RoutineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_routine, parent, false)
        return RoutineViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoutineViewHolder, position: Int) {
        val routine = routines[position]
        holder.bind(routine, onActionClick)
        Log.d("RoutinesAdapter", "Binding routine: ${routine.name}")
    }

    override fun getItemCount(): Int {
        Log.d("RoutinesAdapter", "Item count: ${routines.size}")
        return routines.size
    }

    fun submitList(newRoutines: List<Routine>) {
        routines.clear()
        routines.addAll(newRoutines)
        notifyDataSetChanged()
        Log.d("RoutinesAdapter", "Updated routine list: $newRoutines")
    }

    class RoutineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val routineName: TextView = itemView.findViewById(R.id.routine_name)
        private val startButton: Button = itemView.findViewById(R.id.start_button)
        private val editButton: Button = itemView.findViewById(R.id.edit_button)

        fun bind(routine: Routine, onActionClick: (Routine, String) -> Unit) {
            routineName.text = routine.name
            startButton.setOnClickListener {
                Log.d("RoutinesAdapter", "Start button clicked for routine: ${routine.name}")
                onActionClick(routine, "start")
            }
            editButton.setOnClickListener {
                Log.d("RoutinesAdapter", "Edit button clicked for routine: ${routine.name}")
                onActionClick(routine, "edit")
            }
        }
    }
}
