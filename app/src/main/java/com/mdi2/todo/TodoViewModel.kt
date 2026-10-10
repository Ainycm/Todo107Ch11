package com.mdi2.todo

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    // _task is priv - only this class can access or modify it
    // compose-aware motable list that holds all current task
    private val _task = mutableStateListOf<Task>()
    // counter to generate a unique id for each new taks
    private var _nextId = 1

    // Challenge is declare public value the task
    val task: List<Task>  get() = _task // public read-only value for the task list

    // adds a new task to the list
    fun addTask(title: String){
        if (title.isBlank()) return
        _task.add(Task(id = _nextId++, title = title.trim() ))
    }
    // challenge create the remove task function
    fun removeTask(taskId: Int) {
        _task.removeAll {it.id == taskId}
    }
    // challenge return the number of tasks in the list
    fun containsTask(title: String): Boolean {
        return _task.any {it.title == title}
    }

    fun getTaskCount(): Int {
        return _task.size
    }
}