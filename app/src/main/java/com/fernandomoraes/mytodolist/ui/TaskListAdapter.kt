package com.fernandomoraes.mytodolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fernandomoraes.mytodolist.databinding.ItemTaskBinding
import com.fernandomoraes.mytodolist.model.Task

class TaskListAdapter : ListAdapter<Task,TaskListAdapter.TaskViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TaskViewHolder(private val binding: ItemTaskBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Task) {
            binding.tvTitulo.text = item.titulo
            binding.tvDate.text = "${item.data} ${item.hora}"
        }
    }
}

class DiffCallBack : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Task, newItem: Task) =oldItem.id == newItem.id

}