package com.fernandomoraes.mytodolist.model

data class Task(
    val titulo: String,
    val descricao: String,
    val data: String,
    val hora: String,
    val id: Int = 0,
)
