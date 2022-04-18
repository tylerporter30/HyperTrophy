package com.example.hypertrophy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SuggestedProgramsScreen(navController: NavHostController) {

    var selectedProgram by rememberSaveable{ mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar( title = {
            IconButton(onClick = { navController.navigate(NavRoutes.Templates.route) }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "Templates")
            }
            Text(text = "Programs") },

            actions = {
                var showMenu by rememberSaveable{ mutableStateOf(false) }
                IconButton(onClick = { showMenu = !showMenu }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Drop down menu")
                }

                DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {

                    DropdownMenuItem(onClick = {
                        selectedProgram = "Starting Strength"
                        showMenu = !showMenu
                    }) {
                        Text(text = "Starting Strength")
                    }

                    DropdownMenuItem(onClick = {
                        selectedProgram = "GreySkull"
                        showMenu = !showMenu
                    }) {
                        Text(text = "GreySkull")
                    }

                    DropdownMenuItem(onClick = {
                        selectedProgram = "StrongLifts 5x5"
                        showMenu = !showMenu
                    }) {
                        Text(text = "StrongLifts 5x5")
                    }

                    DropdownMenuItem(onClick = {
                        selectedProgram = "Push Pull Legs"
                        showMenu = !showMenu
                    }) {
                        Text(text = "Push Pull Legs")
                    }

                    DropdownMenuItem(onClick = {
                        selectedProgram = "TGP"
                        showMenu = !showMenu
                    }) {
                        Text(text = "Tyler's Great Program")
                    }
                }
            }

        ) },
        content = {

            ProgramsUI(selectedProgram = selectedProgram)

            if(selectedProgram == "TGP" || selectedProgram == "") {
                AllPrograms(programList = programs)
            }
        }
    )
    FloatingActionButtonComponent(navController = navController)
}

class Exercise(exercise: String, sets: Int, reps: Int) {
    val exercise: String = exercise
    var sets: Int = sets
    var reps: Int = reps
}

@Composable
fun ProgramsUI (selectedProgram: String) {
    if (selectedProgram.equals("Starting Strength")) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Starting Strength")
            WorkoutCard(
                title = "A",
                arrayOf(
                    Exercise("Squat", 3, 5),
                    Exercise("Bench Press", 3, 5),
                    Exercise("Deadlift", 1, 5)
                )
            )

            WorkoutCard(
                title = "B",
                arrayOf(
                    Exercise("Squat", 3, 5),
                    Exercise("Overhead Press", 3, 5),
                    Exercise("Power Clean", 5, 3)
                )
            )


        }
    }

    if (selectedProgram.equals("GreySkull")) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "GreySkull")
            WorkoutCard(
                title = "A",
                exercises = arrayOf(
                    Exercise("Overhead Press", 3, 5),
                    Exercise("Chin up", 3, 5),
                    Exercise("Squat", 3, 5)
                )
            )

            WorkoutCard(
                title = "B",
                exercises = arrayOf(
                    Exercise("Bench Press", 3, 5),
                    Exercise("Barbell Row", 3, 5),
                    Exercise("Deadlift", 1, 5)
                )
            )
        }
    }

    if (selectedProgram.equals("StrongLifts 5x5")) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "StrongLifts 5x5")
            WorkoutCard(
                title = "A",
                arrayOf(
                    Exercise("Squat", 5, 5),
                    Exercise("Bench Press", 5, 5),
                    Exercise("Barbell Row", 5, 5)
                )
            )

            WorkoutCard(
                title = "B",
                arrayOf(
                    Exercise("Squat", 5, 5),
                    Exercise("Overhead Press", 5, 5),
                    Exercise("Deadlift", 1, 5)
                )
            )
        }
    }

    if (selectedProgram.equals("Push Pull Legs")) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Push Pull Legs")
            WorkoutCard(
                title = "Push",
                arrayOf(
                    Exercise("Overhead Press", 3, 12),
                    Exercise("Bench Press", 3, 8),
                    Exercise("Cable Push down", 3, 10)
                )
            )

            WorkoutCard(
                title = "Pull",
                arrayOf(
                    Exercise("Deadlift", 1, 5),
                    Exercise("Chin up", 5, 10),
                    Exercise("Dumbbell Curl", 3, 15)
                )
            )

            WorkoutCard(
                title = "Legs",
                arrayOf(
                    Exercise("Squat", 3, 8),
                    Exercise("Leg Press", 3, 10),
                    Exercise("Leg Curl", 3, 10)
                )
            )
        }
    }


}


@Composable
fun WorkoutCard(
    title: String, 
    exercises: Array<Exercise>
) {
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
                text = AnnotatedString(title),
                style = MaterialTheme.typography.h6,
                onClick = {
                    isOpen = !isOpen
                }
            )

            Column(
                horizontalAlignment = Alignment.Start
            ) {
                if (isOpen) {
                    exercises.forEach {
                        Text(text = it.exercise + ": " + it.sets + " sets of " + it.reps + " reps")
                    }
                }
            }
        }
    }
}


@Composable
fun FloatingActionButtonComponent(navController: NavHostController) {
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp), verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End) {


        FloatingActionButton(
            onClick = {
                navController.navigate(NavRoutes.CreateNewProgram.route)
            },
            modifier = Modifier.size(50.dp), shape = CircleShape, backgroundColor = Color.Black
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                tint = Color.White, contentDescription = "Floating Button",
                modifier = Modifier.size(35.dp)
            )
        }
    }
}


