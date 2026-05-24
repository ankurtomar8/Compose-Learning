package com.example.composelearning.rowscreen

import android.R.id.bold
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun RowScreen() {
    // Arrange items horizontally in a single Row
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "This is one", fontWeight = FontWeight.Bold)
        Text(text = "This is two", fontWeight = FontWeight.Bold)
        Text(text = "This is three", fontWeight = FontWeight.Bold)
    }
}
