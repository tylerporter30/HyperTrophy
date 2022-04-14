package com.example.hypertrophy

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun AnalyzeScreen(navController: NavHostController) {

    var showSquats by rememberSaveable{ mutableStateOf(true) }
    var showDeadlift by rememberSaveable{ mutableStateOf(false) }
    var showBenchPress by rememberSaveable{ mutableStateOf(false) }
    var showOverheadPress by rememberSaveable{ mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(
            title = {
                IconButton(onClick = { navController.navigate(NavRoutes.Home.route) }) {
                    Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
                }
                Text(text = "Analyze")
                    },
            actions = {
                var showMenu by rememberSaveable{ mutableStateOf(false) }
                IconButton(onClick = { showMenu = !showMenu }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Drop down menu")
                }
                
                DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {

                    DropdownMenuItem(onClick = {
                        showSquats = true
                        showDeadlift = false
                        showBenchPress = false
                        showOverheadPress = false

                    }) {
                        Text(text = "Squat")
                    }

                    DropdownMenuItem(onClick = {
                        showSquats = false
                        showDeadlift = true
                        showBenchPress = false
                        showOverheadPress = false                    }) {
                        Text(text = "Deadlift")
                    }

                    DropdownMenuItem(onClick = {
                        showSquats = false
                        showDeadlift = false
                        showBenchPress = true
                        showOverheadPress = false                    }) {
                        Text(text = "Bench Press")
                    }

                    DropdownMenuItem(onClick = {
                        showSquats = false
                        showDeadlift = false
                        showBenchPress = false
                        showOverheadPress = true                    }) {
                        Text(text = "Overhead Press")
                    }

                }
            }
        ) },
        content = {
                  if (showSquats) {
                      graphCard("Squats")
                  }

                  if (showDeadlift) {
                      graphCard("Deadlift")
                  }

                  if (showBenchPress) {

                      graphCard("Bench Press")
                  }

                  if (showOverheadPress) {

                      graphCard("Overhead Press")
                  }


            //AnalyzeContent()
            },
        bottomBar = { BottomBarNavigation(navController = navController) }
    )
}

@Composable
fun AnalyzeContent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        graphCard("Squats")
    }
}

@Composable
fun graphCard(exerciseSelected: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        var showGraph by rememberSaveable { mutableStateOf(true) }
        if (showGraph) {
            Card(
                Modifier
                    .padding(20.dp)
                    .fillMaxWidth(0.8f),
                elevation = 15.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    Text(text = exerciseSelected)
                    Text(text = "\n\n\n\nGraph Will Go Here\n\n\n\n")
                }

                /*Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = { showGraph = false }) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "Hide")
                    }
                }*/ // Commenting this out because we're only showing one graph at a time. If
                // and when we change it to show more than one graph card at a time, we can uncomment this
            }
        }
    }
}