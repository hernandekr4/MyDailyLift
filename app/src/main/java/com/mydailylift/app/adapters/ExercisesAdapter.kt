package com.mydailylift.app.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mydailylift.app.R
import com.mydailylift.app.data.Exercise

class ExercisesAdapter(
    private val onDetailsClick: (Exercise) -> Unit // Lambda for handling details click
) : ListAdapter<Exercise, ExercisesAdapter.ExerciseViewHolder>(ExerciseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view, onDetailsClick)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = getItem(position)
        holder.bind(exercise)
        Log.d("ExercisesAdapter", "Binding exercise: ${exercise.name}")
    }

    class ExerciseViewHolder(
        itemView: View,
        private val onDetailsClick: (Exercise) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView: TextView = itemView.findViewById(R.id.exercise_name)
        private val targetTextView: TextView = itemView.findViewById(R.id.exercise_target)
        private val detailsButton: Button = itemView.findViewById(R.id.details_button)

        fun bind(exercise: Exercise) {
            nameTextView.text = exercise.name
            targetTextView.text = exercise.target
            detailsButton.setOnClickListener { onDetailsClick(exercise) }
        }
    }

    class ExerciseDiffCallback : DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean =
            oldItem == newItem
    }
}
