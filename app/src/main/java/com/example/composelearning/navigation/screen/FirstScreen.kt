package com.example.composelearning.navigation.screen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.composelearning.utils.NavigationScreen


@Composable
fun FirstScreen(navHostController: NavHostController){

    Button(onClick ={
        navHostController.navigate(NavigationScreen.SecondScreen.route)
    }) { }
    Text(text = "First Screen Text")
}