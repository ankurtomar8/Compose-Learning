package com.example.composelearning.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composelearning.ui.theme.ComposeLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLearningTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DashboardScreen(
                        onNavigate = { activityClass ->
                            startActivity(Intent(this, activityClass))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DashboardScreen(onNavigate: (Class<out ComponentActivity>) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Compose Learning Dashboard", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        // List of all your activities
        val activities = listOf(

            "Radio Button Screen" to RadioButtonActivity::class.java,
            "Recycler View Screen" to RecyclerViewActivity::class.java,
            "Row View Screen" to RowViewActivity::class.java,
            "Column View Screen" to ColumnViewActivity::class.java,
            "Navigation Screen" to NavigationViewActivity::class.java,
            "Check Box Screen" to CheckBoxActivity::class.java,
            "Dialog Screen" to DialogActivity::class.java,
            "Pick Image Screen" to PickImageActivity::class.java
        )

        activities.forEach { (label, activityClass) ->
            Button(
                onClick = { onNavigate(activityClass) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(text = label)
            }
        }
    }
}
