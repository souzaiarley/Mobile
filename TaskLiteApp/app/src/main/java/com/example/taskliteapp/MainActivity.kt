package com.example.taskliteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import com.example.taskliteapp.ui.DesafioTaskListScreen
import com.example.taskliteapp.viewmodel.DesafioTaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DesafioTaskListScreen(DesafioTaskViewModel(), Modifier)
        }
    }
}
