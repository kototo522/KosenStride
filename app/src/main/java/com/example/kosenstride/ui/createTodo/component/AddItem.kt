package com.example.kosenstride.ui.createTodo.component

data class AddItem(
    val name: String,
    val value: String,
    val onValueChange: (String) -> Unit,
)
