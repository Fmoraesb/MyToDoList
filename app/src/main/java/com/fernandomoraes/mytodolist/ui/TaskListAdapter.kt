package com.fernandomoraes.mytodolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fernandomoraes.mytodolist.R
import com.fernandomoraes.mytodolist.databinding.ItemTaskBinding
import com.fernandomoraes.mytodolist.model.Task

class TaskListAdapter : ListAdapter<Task,TaskListAdapter.TaskViewHolder>(DiffCallBack()) {
    var listenerEdit : (Task) -> Unit = {}
    var listenerDelete : (Task) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Task) {
            binding.tvTitulo.text = item.titulo
            "${item.data} ${item.hora}".also { binding.tvDate.text = it }
            binding.ivPopupMenu.setOnClickListener {
                showPopUp(item)
            }
        }

        private fun showPopUp(item: Task) {
            val ivPopupMenu = binding.ivPopupMenu
            val popupMenu = PopupMenu(ivPopupMenu.context, ivPopupMenu)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_editar -> listenerEdit(item)
                    R.id.action_deletar -> listenerDelete(item)
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
        }
    }
}

class DiffCallBack : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Task, newItem: Task) =oldItem.id == newItem.id

}