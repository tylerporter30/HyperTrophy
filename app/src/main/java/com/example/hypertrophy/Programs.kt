package com.example.hypertrophy

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun SuggestedProgramsScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar( title = {
            IconButton(onClick = { navController.navigate(NavRoutes.Templates.route) }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "Templates")
            }
            Text(text = "Programs") }) },
        content = {  },
        //bottomBar = { BottomBarNavigation(navController = navController) }
    )
}

