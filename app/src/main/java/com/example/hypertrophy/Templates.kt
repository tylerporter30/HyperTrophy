package com.example.hypertrophy

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun ListOfWorkoutTemplatesScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar( title = {
            IconButton(onClick = { navController.navigate(NavRoutes.Home.route) }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
            }
            Text(text = "Templates")
        }) },
        content = {  },
        bottomBar = { BottomBarNavigation(navController = navController) }
    )
}

@Composable
fun CreateWorkoutTemplateScreen() {

}