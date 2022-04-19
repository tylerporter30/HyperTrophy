package com.example.hypertrophy

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
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
                        showMenu = !showMenu

                    }) {
                        Text(text = "Squat")
                    }

                    DropdownMenuItem(onClick = {
                        showSquats = false
                        showDeadlift = true
                        showBenchPress = false
                        showOverheadPress = false
                        showMenu = !showMenu

                    }) {
                        Text(text = "Deadlift")
                    }

                    DropdownMenuItem(onClick = {
                        showSquats = false
                        showDeadlift = false
                        showBenchPress = true
                        showOverheadPress = false
                        showMenu = !showMenu

                    }) {
                        Text(text = "Bench Press")
                    }

                    DropdownMenuItem(onClick = {
                        showSquats = false
                        showDeadlift = false
                        showBenchPress = false
                        showOverheadPress = true
                        showMenu = !showMenu

                    }) {
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
        Text(
            text = exerciseSelected,
            modifier = Modifier
                .padding(20.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(200.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 15.dp
        ) {
            Box() {
                Canvas(
                    modifier = Modifier
                        .padding(30.dp)
                        .fillMaxSize()
                        .background(color = Color.LightGray)
                ) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    drawLine(
                        start = Offset(x = 0f, y = canvasHeight),
                        end = Offset(x = canvasWidth/4, y = canvasHeight - 100f),
                        color = Color.Black,
                        strokeWidth = 5F
                    )

                    drawLine(
                        start = Offset(x = canvasWidth/4, y = canvasHeight - 100f),
                        end = Offset(x = canvasWidth/2, y = canvasHeight - 150f),
                        color = Color.Black,
                        strokeWidth = 5F
                    )

                    drawLine(
                        start = Offset(x = canvasWidth/2, y = canvasHeight - 150f),
                        end = Offset(x = canvasWidth/4*3, y = canvasHeight - 225f),
                        color = Color.Black,
                        strokeWidth = 5F
                    )

                    drawLine(
                        start = Offset(x = canvasWidth/4*3, y = canvasHeight - 225f),
                        end = Offset(x = canvasWidth, y = canvasHeight - 225f),
                        color = Color.Black,
                        strokeWidth = 5F
                    )

                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(top = 30.dp, bottom = 30.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "225")
                    Text(text = "180")
                    Text(text = "135")
                }

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 30.dp, end = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(text = "4/1")
                    Text(text = "4/3")
                    Text(text = "4/5")
                    Text(text = "4/7")
                    Text(text = "4/9")
                }
            }
        }
    }
}