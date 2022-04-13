package com.example.hypertrophy

sealed class NavRoutes(val route: String) {
    object Welcome: NavRoutes("welcome")
    object Login: NavRoutes("login")
    object SignUp: NavRoutes("signup")
    object Home: NavRoutes("home")
    object Analyze: NavRoutes("analyze")

}

sealed class BottomNavRoutes(val route: String) {
    object Home: BottomNavRoutes("home")
    object Analyze: BottomNavRoutes("analyze")
}