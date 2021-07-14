package com.fernandomoraes.mytodolist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fernandomoraes.mytodolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { TaskListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTasks.adapter = adapter

        insertListener()
        }

    private fun insertListener() {
        binding.fabInserir.setOnClickListener {
            startActivity(Intent(this,AddTaskActivity::class.java))
        }

    }
}
