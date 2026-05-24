package com.example.composelearning.navigation.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composelearning.navigation.screen.FirstScreen
import com.example.composelearning.navigation.screen.SecondScreen
import com.example.composelearning.navigation.screen.ThirdScreen
import com.example.composelearning.utils.NavigationScreen

@Composable
fun StartNavigation(context : Activity){
    // Navigation information

    val  navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = NavigationScreen.FirstScreen.route){
        composable(NavigationScreen.FirstScreen.route){
            FirstScreen(navController)
        }
        composable(NavigationScreen.SecondScreen.route){
            val data = it.arguments?.getString("data")
            SecondScreen(context,navController)
        }
        composable(NavigationScreen.ThirdScreen.route){
            ThirdScreen()
        }
    }
}