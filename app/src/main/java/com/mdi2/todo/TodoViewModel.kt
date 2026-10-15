package com.mdi2.todo

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoViewModel : ViewModel() {

    // _task is priv - only this class can access or modify it
    // compose-aware motable list that holds all current task
    private val _task = mutableStateListOf<Task>()

    // counter to generate a unique id for each new taks
    private var _nextId = 1

    // Challenge is declare public value the task
    val task: List<Task>
        get() = _task // public read-only value for the task list

    // adds a new task to the list
    fun addTask(title: String) {
        if (title.isBlank()) return

        // viewModelScope.launch starts a new coroutine in the viewModelScope
        // Everything inside the launch block will run without blocking the UI
        viewModelScope.launch {

            // withContext switches to the IO dispatcher to run in the background
            // The main thread will not block while waiting for the result
            withContext(Dispatchers.IO) {
                simulatesSlowOperation()
            }

            _task.add(
                Task(
                    id = _nextId++,
                    title = title.trim()
                )
            )
        }
    }

    // challenge create the remove task function
    fun removeTask(taskId: Int) {
        _task.removeAll {
            it.id == taskId
        }
    }

    // challenge return the number of tasks in the list
    fun getTaskCount(): Int {
        return _task.size
    }

    // returns true if a task with the given title exist in the list
    // used in unit test to verify task were added correctly
    fun containsTask(title: String): Boolean {
        return _task.any {
            it.title == title
        }
    }

    // This simulates a slow operation running on the main thread
    // Thread.sleep(5000) pauses the current thread for exactly 5 seconds
    // For testing purposes only -- we will remove this after the demo
    private fun simulatesSlowOperation() {
        Thread.sleep(5000)
    }
}