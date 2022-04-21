package com.example.hypertrophy.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.hypertrophy.data.ExerciseInfo

@Composable
fun Screen_Templates(){

    var typeExpanded by remember { mutableStateOf(false) }

    Scaffold {

        Column {

            Row {

                Row (horizontalArrangement = Arrangement.End){

                    DropdownMenu(
                        expanded = typeExpanded,
                        onDismissRequest = { typeExpanded = false },
                        modifier = Modifier,
                    ) {}
                }

                LazyColumn(){

                }
            }
        }
    }
}


@Composable
fun TemplateExerciseCard(exerciseInfo: ExerciseInfo){

    Column {

        ExerciseInfoCard(exerciseInfo = exerciseInfo )

        LazyColumn(){

        }
    }
}

@Composable
fun TemplateSetRow(){

    Row(horizontalArrangement = Arrangement.SpaceBetween) {

        Text(text = "Set")

        Text(text = "Weight")
        TextField(value = "", onValueChange = {it})
        Text(text = "Reps")
        TextField(value = "", onValueChange = {it})
    }


}