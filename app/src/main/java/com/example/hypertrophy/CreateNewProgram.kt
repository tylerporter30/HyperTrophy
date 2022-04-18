package com.example.hypertrophy

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun CreateNewProgram(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar( title = {
            IconButton(onClick = { navController.navigate(NavRoutes.Programs.route) }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "Create New Program")
            }
            Text(text = "Create New Program") }) },
        content = { },
        //bottomBar = { BottomBarNavigation(navController = navController) }
    )
}