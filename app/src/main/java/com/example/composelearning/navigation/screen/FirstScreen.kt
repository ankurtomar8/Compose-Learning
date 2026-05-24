package com.example.composelearning.navigation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.composelearning.utils.NavigationScreen


@Composable
fun FirstScreen(navHostController: NavHostController){

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "First Screen Text")
        Button(onClick ={
            navHostController.navigate(
                NavigationScreen.SecondScreen.createRoute("Hey"))
        }) {
            Text(text = "Go to Second Screen")
        }

    }
}