package com.example.hypertrophy.programs.startingstrength

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hypertrophy.NavRoutes
import com.example.hypertrophy.history.ExerciseData
import com.example.hypertrophy.history.HistoryCard
import com.example.hypertrophy.history.HistoryCardView
import com.example.hypertrophy.programs.ExerciseRow
import com.example.hypertrophy.viewModel.History
import com.example.hypertrophy.viewModel.HistoryViewModel
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StartingStrengthWorkoutALog(navController: NavHostController,
                                historyViewModel: HistoryViewModel
) {

    var showHistoryCard by rememberSaveable{ mutableStateOf(false) }

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
                        /*historyViewModel.addHistory(
                            history = History(history =
                            todaysDate//historyCard
                            )
                        )
                        navController.navigate(NavRoutes.Analyze.route)
                        *///Should also save the workout somewhere
                            //Need to add the historycardobject to a room db
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
            exercise1SetsAndReps.add(ExerciseRow(setNum = "1", suggestedReps = "5"))
            exercise1SetsAndReps.add(ExerciseRow(setNum = "2", suggestedReps = "5"))
            exercise1SetsAndReps.add(ExerciseRow(setNum = "3", suggestedReps = "5"))
            var exercise1 = ExerciseData(exercisename = "Squat", setsAndReps = exercise1SetsAndReps)
            listOfExercises.add(exercise1)


            Text(text = "Bench Press")
            var exercise2SetsAndReps = ArrayList<String>()
            exercise2SetsAndReps.add(ExerciseRow(setNum = "1", suggestedReps = "5"))
            exercise2SetsAndReps.add(ExerciseRow(setNum = "2", suggestedReps = "5"))
            exercise2SetsAndReps.add(ExerciseRow(setNum = "3", suggestedReps = "5"))
            var exercise2 = ExerciseData(exercisename = "Bench Press", setsAndReps = exercise2SetsAndReps)
            listOfExercises.add(exercise2)

            Text(text = "Deadlift")
            var exercise3SetsAndReps = ArrayList<String>()
            exercise3SetsAndReps.add(ExerciseRow(setNum = "1", suggestedReps = "5"))
            var exercise3 = ExerciseData(exercisename = "Deadlift", setsAndReps = exercise3SetsAndReps)
            listOfExercises.add(exercise3)

            Spacer(modifier = Modifier.height(70.dp))

            var historyCard = HistoryCard(workoutTemplate = "Starting Strength Workout A", date = todaysDate, exercises = listOfExercises)

            if(showHistoryCard) {

                historyViewModel.addHistory(
                    history = History(history = todaysDate)
                )
                navController.navigate(NavRoutes.Analyze.route)


                //HistoryCardView(historyCardObject = historyCard)
                /*historyViewModel.addHistory(
                    history = History(history =
                    "Test"//historyCard
                    )
                )*/
            }
        }
    }
}
