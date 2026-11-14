package com.example.composelearning

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CounterScreen(
    // Injecting the ViewModel automatically
    modifier: Modifier = Modifier,
    myViewModel: CounterViewModel = viewModel()
) {
    // Convert StateFlow into a Compose State that updates the UI automatically
    val currentCount by myViewModel.count.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "You clicked $currentCount times",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = { myViewModel.decrementCounter() }) {
                Text("Decrement")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button({myViewModel.incrementCounter()}){
                Text("Increment")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { myViewModel.resetCounter() }) {
            Text("Reset")
        }
    }
}