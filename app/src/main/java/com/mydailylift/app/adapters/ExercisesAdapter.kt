package com.mydailylift.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mydailylift.app.R
import com.mydailylift.app.data.Exercise


class ExercisesAdapter : ListAdapter<Exercise, ExercisesAdapter.ExerciseViewHolder>(
    ExerciseDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.exercise_name)
        private val targetTextView: TextView = itemView.findViewById(R.id.exercise_target)

        fun bind(exercise: Exercise) {
            nameTextView.text = exercise.name
            targetTextView.text = exercise.target
        }
    }

    class ExerciseDiffCallback : DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean = oldItem == newItem
    }
}
