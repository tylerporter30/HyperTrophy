package com.example.hypertrophy.programs.pushpulllegs

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hypertrophy.NavRoutes
import com.example.hypertrophy.history.ExerciseData
import com.example.hypertrophy.history.HistoryCard
import com.example.hypertrophy.history.HistoryCardView
import com.example.hypertrophy.programs.ExerciseRow
import com.example.hypertrophy.viewModel.HistoryViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LegWorkoutLog(navController: NavHostController,
                  historyViewModel: HistoryViewModel
) {

    var showHistoryCard by rememberSaveable{ mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Leg Workout") }
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
                        //navController.navigate(NavRoutes.Home.route)
                        //Should also save the workout somewhere
                        showHistoryCard = true

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
            val calendar = Calendar.getInstance()

            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)+1
            val year = calendar.get(Calendar.YEAR)
            val todaysDate = "$month/$day/$year"
            Text(text = todaysDate)

            var listOfExercises= ArrayList<ExerciseData>()

            Text(text = "Squat")
            var exercise1SetsAndReps = ArrayList<String>()
            exercise1SetsAndReps.add(ExerciseRow(setNum = "1", suggestedReps = "8"))
            exercise1SetsAndReps.add(ExerciseRow(setNum = "2", suggestedReps = "8"))
            exercise1SetsAndReps.add(ExerciseRow(setNum = "3", suggestedReps = "8"))
            var exercise1 = ExerciseData(exercisename = "Squat", setsAndReps = exercise1SetsAndReps)
            listOfExercises.add(exercise1)


            Text(text = "Leg press")
            var exercise2SetsAndReps = ArrayList<String>()
            exercise2SetsAndReps.add(ExerciseRow(setNum = "1", suggestedReps = "10"))
            exercise2SetsAndReps.add(ExerciseRow(setNum = "2", suggestedReps = "10"))
            exercise2SetsAndReps.add(ExerciseRow(setNum = "3", suggestedReps = "10"))
            var exercise2 = ExerciseData(exercisename = "Leg press", setsAndReps = exercise2SetsAndReps)
            listOfExercises.add(exercise2)

            Text(text = "Leg Curl")
            var exercise3SetsAndReps = ArrayList<String>()
            exercise3SetsAndReps.add(ExerciseRow(setNum = "1", suggestedReps = "10"))
            exercise3SetsAndReps.add(ExerciseRow(setNum = "2", suggestedReps = "10"))
            exercise3SetsAndReps.add(ExerciseRow(setNum = "3", suggestedReps = "10"))
            var exercise3 = ExerciseData(exercisename = "Leg Curl", setsAndReps = exercise3SetsAndReps)
            listOfExercises.add(exercise3)

            Spacer(modifier = Modifier.height(70.dp))

            var historyCard = HistoryCard(workoutTemplate = "Leg Workout", date = todaysDate, exercises = listOfExercises)

            if(showHistoryCard) {
                HistoryCardView(historyCardObject = historyCard)
            }
        }
    }
}