package com.example.composelearning.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.composelearning.rowscreen.RowScreen
import com.example.composelearning.ui.theme.ComposeLearningTheme

class RowViewActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            ComposeLearningTheme() {
                Surface() {
                    RowScreen()
                }
            }
        }

    }

}