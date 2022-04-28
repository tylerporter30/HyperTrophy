package com.example.hypertrophy

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.hypertrophy.ui.theme.HyperTrophyTheme
import com.example.hypertrophy.viewModel.LogViewModel

@Composable
fun LogScreen(navController: NavHostController, viewModel: LogViewModel = viewModel()) {
    Scaffold(
        topBar = { TopAppBar( title = {
            /*IconButton(onClick = { navController.navigate(NavRoutes.Home.route) }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
            }*/
            Text(text = stringResource(R.string.log)) }) },
        //bottomBar = { BottomBarNavigation(navController = navController) },
        content = {
            Box(Modifier.padding(it)) {
                LogScreenUI(viewModel)
            }
        }
    )
}



@Composable
fun LogScreenUI(viewModel: LogViewModel) {
    // TEMP STATE HOLDERS
//    var pickerReps by remember { mutableStateOf(5) }
//    var pickerWeightsInt by remember { mutableStateOf(65) }
//    var pickerWeightsDec by remember { mutableStateOf(0) }
//    var setNumberInt by remember { mutableStateOf(1) }
    var isNumberPickerEnabled by remember { mutableStateOf(false) }

    val exerciseName by viewModel.exerciseNameLive.observeAsState("EXERCISE NAME")
    val setNumberInt by viewModel.setsLive.observeAsState(1)
    val pickerReps by viewModel.repsLive.observeAsState(5)
    val pickerWeightsInt by viewModel.weightsIntLive.observeAsState(65)
    val pickerWeightsDec by viewModel.weightsDecLive.observeAsState(0)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Current Exercise and Set number
        Card(
            modifier = Modifier.padding(top = 40.dp, bottom = 70.dp),
            shape = MaterialTheme.shapes.medium,
            backgroundColor = MaterialTheme.colors.surface,
            border = BorderStroke(2.dp, MaterialTheme.colors.primary),
            elevation = 16.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = exerciseName,
                    modifier = Modifier.padding(all = 8.dp),
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h4
                )
                Text(
                    text = "${ stringResource(R.string.set).uppercase() } $setNumberInt",
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
                Text(stringResource(R.string.reps))
                NumberPicker(
                    value = pickerReps,
                    onValueChange = {
                        if (isNumberPickerEnabled) viewModel.updateReps(it)
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
                Text(stringResource(id = R.string.weight))
                NumberPicker(
                    value = pickerWeightsInt,
                    onValueChange = {
                        if (isNumberPickerEnabled) viewModel.updateWeightsPickerInt(it)
                    },
                    dividersColor =
                    if (isNumberPickerEnabled) MaterialTheme.colors.secondaryVariant else Color.Transparent,
                    range = 0..9999
                )
                Text(".")
                NumberPicker(
                    value = pickerWeightsDec,
                    onValueChange = {
                        if (isNumberPickerEnabled) viewModel.updateWeightsPickerDec(it)
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
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Option to duplicate set
            Button(
                onClick = {
                    viewModel.updateSets()
                },
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(stringResource(R.string.add_set))
            }
            // Option to skip set (mark as incomplete)
            Button(
                onClick = {
                    viewModel.updateSets()
                },
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(stringResource(R.string.skip_set))
            }
        }

        // Big, slap-able button to mark set as complete
        Row(
            modifier = Modifier.padding(top = 50.dp, start = 32.dp, end = 32.dp, bottom = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    viewModel.updateSets()
                },
                //modifier = Modifier.fillMaxSize(),
                modifier = Modifier.fillMaxWidth(),
//                elevation = ButtonElevation.elevation(enabled = true, interactionSource = TODO()),
                //shape = MaterialTheme.shapes.medium,
//                colors = /*TODO*/,
                contentPadding = PaddingValues(12.dp)
            ) {
                Text(stringResource(R.string.done).uppercase())
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = stringResource(R.string.finish_workout))
            }
        }
    }
}

@Preview
@Composable
fun PreviewLogScreenUI() {
    HyperTrophyTheme {
        LogScreenUI(viewModel = LogViewModel())
    }
}