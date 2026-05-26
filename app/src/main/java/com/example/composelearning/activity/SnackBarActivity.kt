package com.example.composelearning.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.composelearning.snackBar.CreateSnackbar
import com.example.composelearning.ui.theme.ComposeLearningTheme

class SnackBarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLearningTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    CreateSnackbar()
                }
            }
        }
    }
}
