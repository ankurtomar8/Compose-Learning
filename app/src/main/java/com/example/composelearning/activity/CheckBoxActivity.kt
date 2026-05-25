package com.example.composelearning.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.composelearning.checkbox.CheckBoxUI
import com.example.composelearning.ui.theme.ComposeLearningTheme

class CheckBoxActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLearningTheme() {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CheckBoxUI()
                }
            }
        }
    }
}