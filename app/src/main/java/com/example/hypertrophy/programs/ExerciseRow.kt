package com.example.hypertrophy.programs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.util.*


@Composable
fun ExerciseRow(setNum: String, suggestedReps: String) : String {
    val focusManager = LocalFocusManager.current

    var numReps by rememberSaveable { mutableStateOf("") }
    var weight by rememberSaveable { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Set $setNum")
        Column(
            Modifier.width(100.dp)
        ) {
            OutlinedTextField(
                value = numReps,
                onValueChange = { num: String ->
                    numReps = num
                },
                placeholder = {
                    Text(text = suggestedReps)
                },
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                label = {
                    Text(text = "Reps")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        Column(
            Modifier.width(100.dp)
        ) {
            OutlinedTextField(
                value = weight,
                onValueChange = { num: String ->
                    weight = num
                },
                /* placeholder = {
                Text(text = "5")
            },*/
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                label = {
                    Text(text = "Weight")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }
    return "Reps: $numReps Weight: $weight"
}
