package com.example.composelearning.navigation.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.composelearning.ui.theme.ComposeLearningTheme

class AnotherActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLearningTheme() {
                Surface() { }
            }
        }
    }
}