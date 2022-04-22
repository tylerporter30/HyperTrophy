package com.example.hypertrophy.history

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import java.util.*

data class HistoryCard(
    var workoutTemplate: String,
    var date: String,
    var exercises: ArrayList<ExerciseData>,
)

data class ExerciseData (
    var exercisename: String,
    var setsAndReps: ArrayList<String>
)

data class ListOfHistory(
    var list: ArrayList<HistoryCard>
)

@Composable
fun HistoryCardView(historyCardObject: HistoryCard) {
    Text(text = historyCardObject.workoutTemplate)
    Text(text = "")
    Text(text = historyCardObject.date)
    Text(text = "\n")
    historyCardObject.exercises.forEach {
        Text(text = it.exercisename)
        Text(text = "")
        it.setsAndReps.forEach {
            Text(text = it)
        }
        Text(text = "\n")
    }
}