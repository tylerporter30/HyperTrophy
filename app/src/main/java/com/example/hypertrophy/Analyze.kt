package com.example.hypertrophy

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
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
    var showBodyWeight by rememberSaveable {mutableStateOf(false)}
    var showBodyFat by rememberSaveable {mutableStateOf(false)}

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
                        showBodyWeight = false
                        showBodyFat = false
                        showMenu = !showMenu

                    }) {
                        Text(text = "Squat")
                    }

                    DropdownMenuItem(onClick = {
                        showSquats = false
                        showDeadlift = true
                        showBenchPress = false
                        showOverheadPress = false
                        showBodyWeight = false
                        showBodyFat = false
                        showMenu = !showMenu

                    }) {
                        Text(text = "Deadlift")
                    }

                    DropdownMenuItem(onClick = {
                        showSquats = false
                        showDeadlift = false
                        showBenchPress = true
                        showOverheadPress = false
                        showBodyWeight = false
                        showBodyFat = false
                        showMenu = !showMenu

                    }) {
                        Text(text = "Bench Press")
                    }

                    DropdownMenuItem(onClick = {
                        showSquats = false
                        showDeadlift = false
                        showBenchPress = false
                        showOverheadPress = true
                        showBodyWeight = false
                        showBodyFat = false
                        showMenu = !showMenu

                    }) {
                        Text(text = "Overhead Press")
                    }

                    DropdownMenuItem(onClick = {
                        showSquats = false
                        showDeadlift = false
                        showBenchPress = false
                        showOverheadPress = false
                        showBodyWeight = true
                        showBodyFat = false
                        showMenu = !showMenu}) {
                        Text(text = "Body Weight")

                    }

                    DropdownMenuItem(onClick = {
                        showSquats = false
                        showDeadlift = false
                        showBenchPress = false
                        showOverheadPress = false
                        showBodyWeight = false
                        showBodyFat = true
                        showMenu = !showMenu}) {
                        Text(text = "Body Fat %")

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

            if (showBodyWeight) {

                graphCardBodyWeight("Body Weight")
            }

            if (showBodyFat) {

                graphCardBodyFat("Body Fat %")
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
                .padding(20.dp),
            style = MaterialTheme.typography.h4
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(200.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 15.dp
        ) {
            Box(Modifier.background(colors.primary.copy(.7f))) {
                Canvas(
                    modifier = Modifier
                        .padding(30.dp)
                        .fillMaxSize()
                        .background(color = Color.White)
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
                    Text(text = "225",color = colors.surface)
                    Text(text = "180",color = colors.surface)
                    Text(text = "135",color = colors.surface)
                }

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 30.dp, end = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(text = "4/1",color = colors.surface)
                    Text(text = "4/3",color = colors.surface)
                    Text(text = "4/5",color = colors.surface)
                    Text(text = "4/7",color = colors.surface)
                    Text(text = "4/9",color = colors.surface)
                }
            }
        }
    }
}

@Composable
fun graphCardBodyWeight(bodyWeightSelected: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = bodyWeightSelected,
            modifier = Modifier
                .padding(20.dp),
            style = MaterialTheme.typography.h4
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .height(300.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 15.dp
        ) {
            Box(Modifier.background(colors.primary.copy(.7f))) {
                Canvas(
                    modifier = Modifier
                        .padding(30.dp)
                        .fillMaxSize()
                        .background(color = Color.White)
                ) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    drawLine(
                        start = Offset(x = 0f, y = 150f),
                        end = Offset(x = canvasWidth/6, y = canvasHeight - 375f),
                        color = Color.Black,
                        strokeWidth = 5F
                    )

                    drawLine(
                        start = Offset(x = canvasWidth/6, y = canvasHeight - 375f),
                        end = Offset(x = canvasWidth/2, y = canvasHeight - 310f),
                        color = Color.Black,
                        strokeWidth = 5F
                    )

                    drawLine(
                        start = Offset(x = canvasWidth/2, y = canvasHeight - 310f),
                        end = Offset(x = canvasWidth/4*3, y = canvasHeight - 245f),
                        color = Color.Black,
                        strokeWidth = 5F
                    )

                    drawLine(
                        start = Offset(x = canvasWidth/4*3, y = canvasHeight - 245f),
                        end = Offset(x = canvasWidth, y = canvasHeight - 130f),
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
                    Text(text = "240",color = colors.surface)
                    Text(text = "230",color = colors.surface)
                    Text(text = "220",color = colors.surface)
                    Text(text = "210",color = colors.surface)
                    Text(text = "200",color = colors.surface)
                    Text(text = "190",color = colors.surface)
                }

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 30.dp, end = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(text = "2/1",color = colors.surface)
                    Text(text = "2/15",color = colors.surface)
                    Text(text = "3/1",color = colors.surface)
                    Text(text = "3/15",color = colors.surface)
                    Text(text = "3/29",color = colors.surface)
                    Text(text = "4/12",color = colors.surface)
                    Text(text = "4/16",color = colors.surface)
                }
            }
        }
    }
}

@Composable
fun graphCardBodyFat(bodyFatSelected: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = bodyFatSelected,
            modifier = Modifier
                .padding(20.dp),
            style = MaterialTheme.typography.h4
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .height(300.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 15.dp
        ) {
            Box(Modifier.background(colors.primary.copy(.7f))) {
                Canvas(
                    modifier = Modifier
                        .padding(30.dp)
                        .fillMaxSize()
                        .background(color = Color.White)
                ) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    drawLine(
                        start = Offset(x = 0f, y = 150f),
                        end = Offset(x = canvasWidth/6, y = canvasHeight - 410f),
                        color = Color.Black,
                        strokeWidth = 5F
                    )

                    drawLine(
                        start = Offset(x = canvasWidth/6, y = canvasHeight - 410f),
                        end = Offset(x = canvasWidth/2, y = canvasHeight - 330f),
                        color = Color.Black,
                        strokeWidth = 5F
                    )

                    drawLine(
                        start = Offset(x = canvasWidth/2, y = canvasHeight - 330f),
                        end = Offset(x = canvasWidth/4*3, y = canvasHeight - 300f),
                        color = Color.Black,
                        strokeWidth = 5F
                    )

                    drawLine(
                        start = Offset(x = canvasWidth/4*3, y = canvasHeight - 300f),
                        end = Offset(x = canvasWidth, y = canvasHeight - 250f),
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
                    Text(text = "30%",color = colors.surface)
                    Text(text = "25%",color = colors.surface)
                    Text(text = "20%",color = colors.surface)
                    Text(text = "15%",color = colors.surface)
                    Text(text = "10%",color = colors.surface)
                }

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 30.dp, end = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(text = "2/1",color = colors.surface)
                    Text(text = "2/15",color = colors.surface)
                    Text(text = "3/1",color = colors.surface)
                    Text(text = "3/15",color = colors.surface)
                    Text(text = "3/29",color = colors.surface)
                    Text(text = "4/12",color = colors.surface)
                    Text(text = "4/16",color = colors.surface)
                }
            }
        }
    }
}