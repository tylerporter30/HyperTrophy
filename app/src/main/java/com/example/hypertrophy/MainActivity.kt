package com.example.hypertrophy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hypertrophy.ui.theme.HyperTrophyTheme
import com.example.hypertrophy.ui.theme.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HyperTrophyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SplashScreen()
                }
            }
        }
    }
}

@Composable
fun SplashScreen() {
    //Create a navController here, send it through to the next screen as a parameter
    val navController = rememberNavController()

    //Implement a splashscreen here, then go to WelcomeScreen()
    MainScreen(navController = navController)
}

@Composable
fun MainScreen(navController: NavHostController) {
    //Create NavHost to navigate between welcome and login/signup
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Welcome.route
    ) {
        composable(NavRoutes.Welcome.route) {
            WelcomeScreen(navController = navController)
        }

        composable(NavRoutes.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(NavRoutes.SignUp.route) {
            SignUpScreen(navController = navController)
        }

        composable(NavRoutes.Home.route) {
            HomeScreen(navController = navController)
        }
    }
}