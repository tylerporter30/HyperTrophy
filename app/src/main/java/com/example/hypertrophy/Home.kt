package com.example.hypertrophy

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(
            title = { Text(text = "Home")},
            actions = {
                IconButton(onClick = { navController.navigate(NavRoutes.Settings.route) }) {
                    Icon(imageVector = Icons.Filled.Settings, contentDescription = "Settings")
                }
                ClickableText(text = AnnotatedString("Logout"), style = TextStyle(color = Color.White), onClick = { navController.navigate(NavRoutes.Login.route) } )

            }
        )},
        content = { HomeContent(navController = navController) },
        bottomBar = { BottomBarNavigation(navController = navController) }
    )
}

@Composable
fun HomeContent(navController: NavHostController) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    Modifier
                        .padding(20.dp)
                        .fillMaxWidth(0.8f),
                    elevation = 15.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(text = "Today's Suggestion:", style = MaterialTheme.typography.h6)
                        ClickableText(
                            text = AnnotatedString("Quads/Glutes"), // There should be some logic for why this workout is selected
                            style = MaterialTheme.typography.h5,
                            onClick = { } //Send to this particular Template
                        )
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    //.padding(bottom = 20.dp, end = 20.dp)
                    .matchParentSize()
            ) {
                IconButton(onClick = {
                    Toast.makeText(
                        context,
                        "Click on a date to view workout details\nClick on a workout name to view the template",
                        Toast.LENGTH_LONG
                    ).show()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = ""
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CompletedWorkoutCard(date = "4/11", title = "Back/Biceps")

            Card(
                Modifier
                    .padding(20.dp)
                    .fillMaxWidth(0.8f),
                elevation = 15.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = "4/9", style = MaterialTheme.typography.h6)
                    ClickableText(
                        text = AnnotatedString("Chest/Triceps"), // This should be a workout completed recently
                        style = MaterialTheme.typography.h5,
                        onClick = { } //Send to this particular Template
                    )
                }
            }
            Card(
                Modifier
                    .padding(20.dp)
                    .fillMaxWidth(0.8f),
                elevation = 15.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = "4/7", style = MaterialTheme.typography.h6)
                    ClickableText(
                        text = AnnotatedString("Back/Biceps"), // This should be a workout completed recently
                        style = MaterialTheme.typography.h5,
                        onClick = { } //Send to this particular Template
                    )
                }
            }
            Card(
                Modifier
                    .padding(20.dp)
                    .fillMaxWidth(0.8f),
                elevation = 15.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = "4/5", style = MaterialTheme.typography.h6)
                    ClickableText(
                        text = AnnotatedString("Chest/Triceps"), // This should be a workout completed recently
                        style = MaterialTheme.typography.h5,
                        onClick = { } //Send to this particular Template
                    )
                }
            }
            Card(
                Modifier
                    .padding(20.dp)
                    .fillMaxWidth(0.8f),
                elevation = 15.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = "4/3", style = MaterialTheme.typography.h6)
                    ClickableText(
                        text = AnnotatedString("Quads/Glutes"), // This should be a workout completed recently
                        style = MaterialTheme.typography.h5,
                        onClick = { } //Send to this particular Template
                    )
                }
            }
            Card(
                Modifier
                    .padding(20.dp)
                    .fillMaxWidth(0.8f),
                elevation = 15.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = "4/1", style = MaterialTheme.typography.h6)
                    ClickableText(
                        text = AnnotatedString("Back/Biceps"), // This should be a workout completed recently
                        style = MaterialTheme.typography.h5,
                        onClick = { } //Send to this particular Template
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun CompletedWorkoutCard(date: String, title: String) {
    val context = LocalContext.current
    Card(
        Modifier
            .padding(20.dp)
            .fillMaxWidth(0.8f),
        elevation = 15.dp,
        shape = RoundedCornerShape(10.dp),

    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            var isOpen by rememberSaveable{ mutableStateOf(false) }

            ClickableText(
                text = AnnotatedString(date),
                style = MaterialTheme.typography.h6,
                onClick = {
                    Toast.makeText(context, "This will expand the card", Toast.LENGTH_LONG).show()
                    isOpen = !isOpen
                } //Expand Card to show the details of this completed workout
            )

            ClickableText(
                text = AnnotatedString(title), // This should be a workout completed recently
                style = MaterialTheme.typography.h5,
                onClick = { Toast.makeText(context, "This will navigate to this template", Toast.LENGTH_LONG).show() } //Send to this particular Template
            )

            if (isOpen) {
                Text(text =
                        "Exercise\n" +
                        "Set 1: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Set 2: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Set 3: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Set 4: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Set 5: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Exercise\n" +
                        "Set 1: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Set 2: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Set 3: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Set 4: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Set 5: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Exercise\n" +
                        "Set 1: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Set 2: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Set 3: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Set 4: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Set 5: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Exercise\n" +
                        "Set 1: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Set 2: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Set 3: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Set 4: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Set 5: 8 Reps: 155 lbs: RPE: 7\n" +
                        "Set 6: 8 Reps: 155 lbs: RPE: 7"
                )
            }
        }
    }
}