package com.example.hypertrophy.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.hypertrophy.data.Exercise
import com.example.hypertrophy.data.HistoryRecord
import com.example.hypertrophy.data.Sets
import com.example.hypertrophy.data.Template


@Composable
fun Screen_History(){

    var listOfHistoryRecord = listOf<HistoryRecord>()

    Scaffold {

        LazyColumn{
            
            items(listOfHistoryRecord){

                Text(text = it.date)
                TemplateOfHistory(templates = it.templates)
            }
        }
    }
}



@Composable
fun TemplateOfHistory(templates:List<Template>){

    LazyColumn{

        items(templates){

            Text(text = it.templateName)
            ExerciseOfTemplate(it.listOfExercise)
        }
    }
}



@Composable
fun ExerciseOfTemplate(listOfExercise: List<Exercise>){

    LazyColumn{

        items(listOfExercise){

            Text(text = it.exerciseInfo.name)
            it.listOfSets
        }
    }
}

@Composable
fun SetOfExercise(listOfSets : List<Sets>){

    LazyColumn {

        items(listOfSets){

            Row {
                Text(text = "Set ${it.setNum}")
                Text(text = "Weight: ${it.Weight}")
                Text(text = "RPS: ${it.rpe}")
                Text(text = "resp: ${it.reps}")
            }
        }
    }
}