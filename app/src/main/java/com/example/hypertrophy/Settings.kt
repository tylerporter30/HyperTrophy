package com.example.hypertrophy

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun SettingsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
                 TopAppBar(
                     title = {
                         IconButton(onClick = { navController.navigate(NavRoutes.Home.route) }) {
                             Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Arrow")
                         }
                         Text(text = "Settings")
                     }
                 )
        },
        content = { SettingsUI() }
    )
}

@Composable
fun SettingsUI() {

}