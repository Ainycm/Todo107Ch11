package com.mdi2.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mdi2.todo.ui.theme.TodoTheme

class MainActivity : ComponentActivity() {

    private val _viewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // This line causes the memory leak -- storing Activity context in a singleton
        AppState.initialize(context = this) // 'this' refers to the Activity

        setContent {
            TodoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoScreen(
                        viewModel = _viewModel,
                        modifier = Modifier.safeDrawingPadding()
                    )
                }
            }
        }
    }
}