package com.example.hypertrophy

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun CreateNewTemplate(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(
            title = {
                IconButton(onClick = { navController.navigate(NavRoutes.CreateNewProgram.route) }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Arrow")
                }
                Text(text = "Create New Template")
            }
        ) },
        content = { CreateWorkoutTemplateScreen(navController = navController) }
    )
}

@Composable
fun CreateWorkoutTemplateScreen(navController: NavHostController) {
    Column() {
        //Add Exercise
        Button(onClick = { navController.navigate(NavRoutes.Browse.route) }) {
            Text(text = "Add Exercise")
        }
    }
}