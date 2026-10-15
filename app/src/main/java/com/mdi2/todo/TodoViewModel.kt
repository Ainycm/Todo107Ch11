package com.mdi2.todo

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {

    companion object {
        private const val TAG = "TodoViewModel"
    }

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

        if (title.isNotBlank()) {

            _task.add(
                Task(
                    id = _nextId++,
                    title = title.trim()
                )
            )

            // Log.i: information message - useful during development
            Log.i(
                TAG,
                "Task added: $title"
            )

        } else {

            // Log.w warning message - useful for indicating potential issues or unexpected behavior
            Log.w(
                TAG,
                "addTask() called with blank title -- task was not added"
            )
        }
    }

    // challenge create the removeTask function
    fun removeTask(taskId: Int) {

        _task.removeAll {
            it.id == taskId
        }

        Log.i(
            TAG,
            "Task removed: $taskId"
        )
    }

    // challenge return the number of tasks in the list
    // use in unit test to verify task were added correctly
    fun getTaskCount(): Int = _task.size

    // returns true if a task with the given title exist in the list
    // used in unit test to verify task were added correctly
    fun containsTask(title: String): Boolean {

        return _task.any {
            it.title == title
        }
    }
}