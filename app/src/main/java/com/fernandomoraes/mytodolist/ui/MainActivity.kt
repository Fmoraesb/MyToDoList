package com.fernandomoraes.mytodolist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fernandomoraes.mytodolist.databinding.ActivityMainBinding
import com.fernandomoraes.mytodolist.datasource.TaskDataSource

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { TaskListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListener()
        }

    private fun insertListener() {
        binding.fabInserir.setOnClickListener {
            startActivityForResult(Intent(this,AddTaskActivity::class.java),CREATE_TASK)
        }
        adapter.listenerEdit = {

        }
        adapter.listenerDelete = {

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_TASK) {
            binding.rvTasks.adapter = adapter
            adapter.submitList(TaskDataSource.getList())
        }
    }

    companion object {
        private const val CREATE_TASK = 100
    }
}
