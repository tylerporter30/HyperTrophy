package com.example.hypertrophy

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun SignUpScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Sign Up") }) },
        content = {  }
    )
}