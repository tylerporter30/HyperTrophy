package com.example.hypertrophy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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

                }
            }

        ) },
        content = {
            ProgramsUI(selectedProgram = selectedProgram)


        }
    )
    FloatingActionButtonComponent(navController = navController)
}

class Exercise(s: String, i: Int, i1: Int) {
    val exercise: String = s
    var sets: Int = i
    var reps: Int = i1
}

@Composable
fun ProgramsUI (selectedProgram: String) {
    if (selectedProgram.equals("Starting Strength")) {
        Column(
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
        }
    }

    if (selectedProgram.equals("GreySkull")) {
        Text(text = "GreySkull")
    }

    if (selectedProgram.equals("StrongLifts 5x5")) {
        Text(text = "StrongLifts 5x5")
    }

    if (selectedProgram.equals("Push Pull Legs")) {
        Text(text = "Push Pull Legs")
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

            if (isOpen) {
                exercises.forEach {
                    Text(text = it.exercise + ": " + it.sets + " sets of " + it.reps + " reps")
                }
                    
                    
               /* exercise1 + " \n" +
                sets1 + " Sets " +
                " of " +
                reps1 + " Reps\n" + 
                        
                exercise2 + " \n" +
                sets2 + " Sets " +
                " of " +
                reps2 + " Reps\n" +
               
                exercise3 + " \n" +
                sets3 + " Sets " +
                " of " +
                reps3 + " Reps\n"*/
                //)
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


