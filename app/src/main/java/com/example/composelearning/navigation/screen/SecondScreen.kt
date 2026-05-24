package com.example.composelearning.navigation.screen

import android.app.Activity
import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.composelearning.navigation.navigation.AnotherActivity


@Composable
fun SecondScreen(context: Activity){

    Button(onClick = {
        val intent = Intent(context, AnotherActivity::class.java)
        context.startActivity(intent)
    })
    {
        Text(text = " Got to AnotherSecond Screen")
    }
    Text(text = "In Second Screen")
}