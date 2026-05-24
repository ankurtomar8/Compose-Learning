package com.example.composelearning.utils

sealed class NavigationScreen(val route: String) {

    object FirstScreen : NavigationScreen("firstscreen")
    object SecondScreen : NavigationScreen("{data}/secondscreen"){
       fun createRoute(data:String) = "$data/secondscreen"
    }
    object ThirdScreen : NavigationScreen("thirdscreen")


}