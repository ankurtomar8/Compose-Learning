package com.example.composelearning.navigation.screen

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import com.example.composelearning.navigation.navigation.AnotherActivity
import com.example.composelearning.utils.NavigationScreen


@Composable
fun SecondScreen(context: Activity,navHostController: NavHostController){

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            navHostController.navigate(NavigationScreen.ThirdScreen.route){
                popUpTo(NavigationScreen.FirstScreen.route){
                    inclusive = true // remove every back stack
                    //      launchSingleTop = true // No repeated composable fun in stack
                }
            }
        })
        {
            Text(text = " Got to AnotherSecond Screen")
        }
        Text(text = "In Second Screen")
    }
}