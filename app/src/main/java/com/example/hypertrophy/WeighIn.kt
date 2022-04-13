package com.example.hypertrophy

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun WeighInScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar( title = { Text(text = "Weigh In") }) },
        content = {  },
        bottomBar = { BottomBarNavigation(navController = navController) }
    )
}