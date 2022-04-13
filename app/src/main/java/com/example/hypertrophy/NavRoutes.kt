package com.example.hypertrophy

sealed class NavRoutes(val route: String) {
    object Welcome: NavRoutes("welcome")
    object Login: NavRoutes("login")
    object SignUp: NavRoutes("signup")
}