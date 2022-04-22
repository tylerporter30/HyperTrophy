package com.example.hypertrophy.programs.startingstrength

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hypertrophy.NavRoutes
import com.example.hypertrophy.programs.ExerciseRow

@Composable
fun StartingStrengthWorkoutALog(navController: NavHostController) {

    /*var squatsSet1Reps by rememberSaveable{ mutableStateOf("") }
    var squatsSet2Reps by rememberSaveable{ mutableStateOf("") }
    var squatsSet3Reps by rememberSaveable{ mutableStateOf("") }
    var benchPressSet1Reps by rememberSaveable{ mutableStateOf("") }
    var benchPressSet2Reps by rememberSaveable{ mutableStateOf("") }
    var benchPressSet3Reps by rememberSaveable{ mutableStateOf("") }
    var deadliftSet1Reps by rememberSaveable{ mutableStateOf("") }

    var squatsSet1Weight by rememberSaveable{ mutableStateOf("") }
    var squatsSet2Weight by rememberSaveable{ mutableStateOf("") }
    var squatsSet3Weight by rememberSaveable{ mutableStateOf("") }
    var benchPressSet1Weight by rememberSaveable{ mutableStateOf("") }
    var benchPressSet2Weight by rememberSaveable{ mutableStateOf("") }
    var benchPressSet3Weight by rememberSaveable{ mutableStateOf("") }
    var deadliftSet1Weight by rememberSaveable{ mutableStateOf("") }*/

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Starting Strength Workout A") }
            )
        },
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                Button(
                    onClick = {
                        navController.navigate(NavRoutes.Home.route)
                        //Should also save the workout somewhere
                    }
                ) {
                    Text(text = "Finish Workout")
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            Text(text = "Squat")

            ExerciseRow(setNum = "1", suggestedReps = "5")
            ExerciseRow(setNum = "2", suggestedReps = "5")
            ExerciseRow(setNum = "3", suggestedReps = "5")

            Text(text = "Bench Press")
            ExerciseRow(setNum = "1", suggestedReps = "5")
            ExerciseRow(setNum = "2", suggestedReps = "5")
            ExerciseRow(setNum = "3", suggestedReps = "5")

            Text(text = "Deadlift")
            ExerciseRow(setNum = "1", suggestedReps = "5")

            Spacer(modifier = Modifier.height(70.dp))
        }
    }
}
