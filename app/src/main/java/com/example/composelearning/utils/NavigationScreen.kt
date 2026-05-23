package com.example.composelearning.utils

sealed class NavigationScreen(val route: String) {

    object FirstScreen : NavigationScreen("firstscreen")
    object SecondScreen : NavigationScreen("secondscreen")


}