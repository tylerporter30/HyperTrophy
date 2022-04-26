package com.example.hypertrophy

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hypertrophy.data.ExerciseInfo
import com.example.hypertrophy.data.Sets
import com.example.hypertrophy.data.Template
import com.example.hypertrophy.programs.ProgramNavRoutes

@Composable
fun SuggestedProgramsScreen(navController: NavHostController) {

    var selectedProgram by rememberSaveable{ mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar( title = {
            IconButton(onClick = { navController.navigate(NavRoutes.Home.route) }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
            }
            Text(text = stringResource(R.string.programs)) },

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
        bottomBar = { BottomBarNavigation(navController = navController) },
        content = {

            ProgramsUI(selectedProgram = selectedProgram, navController = navController)

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
fun ProgramsUI (selectedProgram: String, navController: NavHostController) {
    if (selectedProgram.equals("Starting Strength")) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Starting Strength")
            WorkoutCard(
                title = "A",
                workout = "StartingStrengthWorkoutA",
                arrayOf(
                    Exercise("Squat", 3, 5),
                    Exercise("Bench Press", 3, 5),
                    Exercise("Deadlift", 1, 5)
                ),
                navController = navController
            )
            /*val startingStrengthTemplateA = Template(
                templateName = "Starting Strength A",
                listOfExercise = listOf(
                    com.example.hypertrophy.data.Exercise(
                        exerciseInfo = ExerciseInfo(
                            name = "barbell full squat",
                            bodyPart = ExerciseInfo.
                        ),
                        listOfSets = listOf(
                            Sets(
                                setNum = 1,
                                reps = 5
                            )
                        )
                    )
                )
            )*/

            WorkoutCard(
                title = "B",
                workout = "StartingStrengthWorkoutB",
                arrayOf(
                    Exercise("Squat", 3, 5),
                    Exercise("Overhead Press", 3, 5),
                    Exercise("Power Clean", 5, 3)
                ),
                navController = navController
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
                workout = "GreySkullWorkoutA",
                exercises = arrayOf(
                    Exercise("Overhead Press", 3, 5),
                    Exercise("Chin up", 3, 5),
                    Exercise("Squat", 3, 5)
                ),
                navController = navController
            )

            WorkoutCard(
                title = "B",
                workout = "GreySkullWorkoutB",
                exercises = arrayOf(
                    Exercise("Bench Press", 3, 5),
                    Exercise("Barbell Row", 3, 5),
                    Exercise("Deadlift", 1, 5)
                ),
                navController = navController
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
                workout = "StrongLiftsWorkoutA",
                arrayOf(
                    Exercise("Squat", 5, 5),
                    Exercise("Bench Press", 5, 5),
                    Exercise("Barbell Row", 5, 5)
                ),
                navController = navController
            )

            WorkoutCard(
                title = "B",
                workout = "StrongLiftsWorkoutB",
                arrayOf(
                    Exercise("Squat", 5, 5),
                    Exercise("Overhead Press", 5, 5),
                    Exercise("Deadlift", 1, 5)
                ),
                navController = navController
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
                workout = "PushWorkout",
                arrayOf(
                    Exercise("Overhead Press", 3, 12),
                    Exercise("Bench Press", 3, 8),
                    Exercise("Cable Push down", 3, 10)
                ),
                navController = navController
            )

            WorkoutCard(
                title = "Pull",
                workout = "PullWorkout",
                arrayOf(
                    Exercise("Deadlift", 1, 5),
                    Exercise("Chin up", 5, 10),
                    Exercise("Dumbbell Curl", 3, 15)
                ),
                navController = navController
            )

            WorkoutCard(
                title = "Legs",
                workout = "LegWorkout",
                arrayOf(
                    Exercise("Squat", 3, 8),
                    Exercise("Leg Press", 3, 10),
                    Exercise("Leg Curl", 3, 10)
                ),
                navController = navController
            )
        }
    }


}


@Composable
fun WorkoutCard(
    title: String,
    workout: String,
    exercises: Array<Exercise>,
    navController: NavHostController
) {

    var isOpen by rememberSaveable{ mutableStateOf(false) }

    Card(
        Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .clickable(onClick = { isOpen = !isOpen }),
        elevation = 15.dp,
        shape = RoundedCornerShape(10.dp),

        ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(text = title, style = MaterialTheme.typography.h6)
            /*ClickableText(
                text = AnnotatedString(title),
                style = MaterialTheme.typography.h6,
                onClick = {
                    isOpen = !isOpen
                }
            )*/

            Column(
                horizontalAlignment = Alignment.Start
            ) {
                if (isOpen) {
                    exercises.forEach {
                        Text(text = it.exercise + ": " + it.sets + " sets of " + it.reps + " reps")
                    }
                    var startWorkout by rememberSaveable { mutableStateOf(false)}
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(onClick = { startWorkout = true }) {
                            Text(text = "Start")
                        }
                    }
                    if(startWorkout) {
                        StartWorkout(
                            navController = navController,
                            workout = workout,
                            //exercises = exercisesu
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StartWorkout(
    navController: NavHostController,
    workout: String,
    //exercises: Array<Exercise>
) {
    if(workout == "StartingStrengthWorkoutA") {
        navController.navigate(ProgramNavRoutes.StartingStrengthWorkoutA.route)
    }
    if(workout == "StartingStrengthWorkoutB") {
        navController.navigate(ProgramNavRoutes.StartingStrengthWorkoutB.route)
    }
    if(workout == "GreySkullWorkoutA") {
        navController.navigate(ProgramNavRoutes.GreySkullWorkoutA.route)
    }
    if(workout == "GreySkullWorkoutB") {
        navController.navigate(ProgramNavRoutes.GreySkullWorkoutB.route)
    }
    if(workout == "StrongLiftsWorkoutA") {
        navController.navigate(ProgramNavRoutes.StrongLiftsWorkoutA.route)
    }
    if(workout == "StrongLiftsWorkoutB") {
        navController.navigate(ProgramNavRoutes.StrongLiftsWorkoutB.route)
    }
    if(workout == "PushWorkout") {
        navController.navigate(ProgramNavRoutes.PushWorkout.route)
    }
    if(workout == "PullWorkout") {
        navController.navigate(ProgramNavRoutes.PullWorkout.route)
    }
    if(workout == "LegWorkout") {
        navController.navigate(ProgramNavRoutes.LegWorkout.route)
    }

}

@Composable
fun FloatingActionButtonComponent(navController: NavHostController) {

    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 70.dp, end = 10.dp), verticalArrangement = Arrangement.Bottom,
        //.padding(10.dp), verticalArrangement = Arrangement.Bottom,
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


