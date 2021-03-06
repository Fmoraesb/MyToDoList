package com.fernandomoraes.mytodolist.ui

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fernandomoraes.mytodolist.R
import com.fernandomoraes.mytodolist.databinding.ActivityAddTaskBinding
import com.fernandomoraes.mytodolist.datasource.TaskDataSource
import com.fernandomoraes.mytodolist.extensions.format
import com.fernandomoraes.mytodolist.extensions.text
import com.fernandomoraes.mytodolist.model.Task
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AddTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra(TASK_ID)) {
            val taskId = intent.getIntExtra(TASK_ID, 0)
            TaskDataSource.findById(taskId)?.let {
                binding.tlTitulo.text = it.titulo
                binding.tlDetalhes.text = it.descricao
                binding.tlData.text = it.data
                binding.tlHora.text = it.hora
                binding.btCriar.text = getString(R.string.text_alterar_tarefa)
            }
        }
        insertListeners()
    }

    private fun insertListeners() {
        binding.tlData.editText?.setOnClickListener {
             val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.tlData.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        binding.tlHora.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            timePicker.addOnPositiveButtonClickListener {
                val hora = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour
                val minuto = if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
                binding.tlHora.text = "$hora:$minuto"
            }
            timePicker.show(supportFragmentManager, null)
        }

        binding.btCancelar.setOnClickListener {
            finish()
        }

        binding.btCriar.setOnClickListener {
            val task = Task(
                titulo = binding.tlTitulo.text,
                descricao = binding.tlDetalhes.text,
                data = binding.tlData.text,
                hora = binding.tlHora.text,
                id = intent.getIntExtra(TASK_ID, 0)
            )
            TaskDataSource.insertTask(task)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    companion object {
        const val TASK_ID = "task_id"
    }
}