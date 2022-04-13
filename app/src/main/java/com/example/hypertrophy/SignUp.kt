package com.example.hypertrophy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun SignUpScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Sign Up") }) },
        content = { SignUpContent(navController = navController) }
    )
}

@Composable
fun SignUpContent(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Sign Up")
        Button(onClick = { navController.navigate(NavRoutes.Home.route) }) {
            Text(text = "Sign Up")
        }
    }
}