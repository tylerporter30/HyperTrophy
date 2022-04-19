package com.example.hypertrophy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun ListOfWorkoutTemplatesScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(
            title = {
                IconButton(onClick = { navController.navigate(NavRoutes.Home.route) }) {
                    Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
                }
                Text(text = "Templates")
            },
            actions = {
                var showMenu by rememberSaveable{ mutableStateOf(false) }
                IconButton(onClick = { showMenu = !showMenu }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Drop down menu")
                }

                DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {

                    DropdownMenuItem(onClick = {
                        navController.navigate(NavRoutes.Programs.route)
                    }) {
                        Text(text = "Programs")
                    }

                    DropdownMenuItem(onClick = {
                        navController.navigate(NavRoutes.Equipment.route)
                    }) {
                        Text(text = "Equipment")
                    }



                }
            }
            ) },
        content = { templatesUI(navController = navController) },
        bottomBar = { BottomBarNavigation(navController = navController) }
    )
}

@Composable
fun templatesUI(navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { navController.navigate(NavRoutes.Log.route) }) {
            Text(text = "This is a temporary button for development purposes. Click here to see the workout Log screen")
        }
    }
}

