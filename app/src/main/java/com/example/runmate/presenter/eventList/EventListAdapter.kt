package com.example.runmate.presenter.eventList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.runmate.data.model.TrainingModel
import com.example.runmate.databinding.EventListItemBinding
import com.example.runmate.util.formatDate

class EventListAdapter(

): ListAdapter<TrainingModel, EventListAdapter.EventViewHolder>(EventDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = EventListItemBinding.inflate(inflater, parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class EventViewHolder(
        private val binding: EventListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(event: TrainingModel) = with(binding) {
            binding.eventCalories.text = event.calories.toString()
            binding.eventDate.text = formatDate(event.startAt)
            binding.eventSpeed.text = event.averageSpeed.toString()
            binding.eventDistance.text = event.distance.toString()
            // binding.eventImage.sourceLayoutResId
            // binding.eventOpenButton
        }
    }

    class EventDiffUtil: DiffUtil.ItemCallback<TrainingModel>() {

        override fun areItemsTheSame(oldItem: TrainingModel, newItem: TrainingModel): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: TrainingModel, newItem: TrainingModel): Boolean = oldItem == newItem
    }
}