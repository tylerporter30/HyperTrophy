package com.example.hypertrophy

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.hypertrophy.ui.theme.HyperTrophyTheme

@Composable
fun LogScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar( title = {
            IconButton(onClick = { navController.navigate(NavRoutes.Home.route) }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
            }
            Text(text = "Log") }) },
        content = {
            Box(Modifier.padding(it)) {
                LogScreenUI()
            }
        },
        bottomBar = { BottomBarNavigation(navController = navController) }
    )
}



@Composable
fun LogScreenUI() {
    // TEMP STATE HOLDERS
    var pickerReps by remember { mutableStateOf(5) }
    var pickerWeightsInt by remember { mutableStateOf(65) }
    var pickerWeightsDec by remember { mutableStateOf(0) }
    var setNumberInt by remember { mutableStateOf(1) }
    var isNumberPickerEnabled by remember { mutableStateOf(false) }

    val exerciseString = "EXERCISE NAME"
    val setNumberString = "SET"

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Current Exercise and Set number
        Card(
            modifier = Modifier.padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            backgroundColor = MaterialTheme.colors.surface,
            border = BorderStroke(2.dp, MaterialTheme.colors.primaryVariant),
            elevation = 16.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = exerciseString,
                    modifier = Modifier.padding(all = 8.dp),
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h4
                )
//                Spacer(Modifier.height(16.dp))
                Text(
                    text = "$setNumberString $setNumberInt",
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = MaterialTheme.typography.h6
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isNumberPickerEnabled = !isNumberPickerEnabled },
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Target reps, editable
                Text("Reps")
                NumberPicker(
                    value = pickerReps,
                    onValueChange = {
                        if (isNumberPickerEnabled) pickerReps = it
                    },
                    dividersColor =
                    if (isNumberPickerEnabled) MaterialTheme.colors.secondaryVariant else Color.Transparent,
                    range = 1..999
                )
            }
//            Spacer(Modifier.width(8.dp))
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Target weight, editable
                Text("Weight")
                NumberPicker(
                    value = pickerWeightsInt,
                    onValueChange = {
                        if (isNumberPickerEnabled) pickerWeightsInt = it
                    },
                    dividersColor =
                    if (isNumberPickerEnabled) MaterialTheme.colors.secondaryVariant else Color.Transparent,
                    range = 0..9999
                )
                Text(".")
                NumberPicker(
                    value = pickerWeightsDec,
                    onValueChange = {
                        if (isNumberPickerEnabled) pickerWeightsDec = it
                    },
                    dividersColor =
                    if (isNumberPickerEnabled) MaterialTheme.colors.secondaryVariant else Color.Transparent,
                    range = 0..9
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Option to duplicate set
            Button(
                onClick = {
                    /*TODO*/
                    setNumberInt++
                },
                contentPadding = PaddingValues(16.dp)
            ) {
                Text("Add Set")
            }
            // Option to skip set (mark as incomplete)
            Button(
                onClick = {
                    /*TODO*/
                    setNumberInt++
                },
                contentPadding = PaddingValues(16.dp)
            ) {
                Text("Skip Set")
            }
        }

//        Spacer(modifier = Modifier.height(32.dp))

        // Big, slap-able button to mark set as complete
        Row(
            modifier = Modifier.padding(32.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    /*TODO*/
                    setNumberInt++
                },
                modifier = Modifier.fillMaxSize(),
//                elevation = ButtonElevation.elevation(enabled = true, interactionSource = TODO()),
                shape = MaterialTheme.shapes.medium,
//                colors = /*TODO*/,
                contentPadding = PaddingValues(8.dp)
            ) {
                Text("DONE")
            }
        }
    }
}

@Preview
@Composable
fun PreviewLogScreenUI() {
    HyperTrophyTheme {
        LogScreenUI()
    }
}