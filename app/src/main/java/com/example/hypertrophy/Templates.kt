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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.hypertrophy.data.Template

@Composable
fun ListOfWorkoutTemplatesScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(
            title = {
                IconButton(onClick = { navController.navigate(NavRoutes.Home.route) }) {
                    Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
                }
                Text(text = stringResource(R.string.templates))
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
                        Text(text = stringResource(R.string.programs))
                    }

                    DropdownMenuItem(onClick = {
                        navController.navigate(NavRoutes.Equipment.route)
                    }) {
                        Text(text = stringResource(R.string.equipment))
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

    }
}

