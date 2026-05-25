package com.example.composelearning.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.composelearning.radioButton.RadioButtonGroup

class RadioButtonActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val radioOptions = listOf("Option 1", "Option 2", "Option 3", "Option 4")
                    var selectedOption by remember { mutableStateOf(radioOptions[2]) }
                    
                    RadioButtonGroup(
                        options = radioOptions,
                        selectedOption = selectedOption,
                        onOptionSelected = { selectedOption = it }
                    )
                }
            }
        }
    }
}
