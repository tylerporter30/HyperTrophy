package com.example.hypertrophy


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.navigation.NavHostController
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.hypertrophy.ui.theme.HyperTrophyTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

/**
 * Four points of user input:
 *  Current weight
 *  Track daily Calories
 *  Body part measurements (for hypertrophy tracking)
 *  Body Fat Percentage
 *
 * Units for each input must be adaptable for user preference
 */
@Composable
fun WeighInScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(title = {
            IconButton(onClick = { navController.navigate(NavRoutes.Home.route) }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
            }
            Text(text = "Weigh In") }) },
        content = {
            Box(Modifier.padding(it)) {
                WeighInScreenUI()
            }
        },
        bottomBar = { BottomBarNavigation(navController = navController) }
    )
}

// @ExperimentalPagerApi
@Composable
fun WeighInScreenUI() {
    var tabIndex by remember { mutableStateOf(0) }
    val titles = listOf("Weight", "Diet", "Measure", "Body Fat %")
//    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
//            indicator = { tabPositions ->
//                TabRowDefaults.Indicator(Modifier.pagerTabIndicatorOffset(pagerState, tabPositions))
//            }
//            divider = { },
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
//        HorizontalPager(
//            count = titles.size,
//            state = pagerState
//        ) { tabIndex ->
//            when (tabIndex) {
//                0 -> WeighInWeight()
//                1 -> WeighInDiet()
//                2 -> WeighInMeasurements()
//                3 -> WeighInBodyFat()
//                else -> WeighInWeight()
//            }
//        }
        when (tabIndex) {
            0 -> WeighInWeight()
            1 -> WeighInDiet()
            2 -> WeighInMeasurements()
            3 -> WeighInBodyFat()
            else -> WeighInWeight()
        }
    }
}

@Composable
fun WeighInWeight() {
    // TEMP STATE HOLDERS
    var pickerPercentInt by remember { mutableStateOf(189) }
    var pickerPercentDec by remember { mutableStateOf(5) }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Current Weight",
            modifier = Modifier.padding(vertical = 16.dp),
            style = MaterialTheme.typography.h4
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumberPicker(
                value = pickerPercentInt,
                onValueChange = { pickerPercentInt = it },
                dividersColor = MaterialTheme.colors.secondary,
                range = 0..999
            )
            Text(".")
            NumberPicker(
                value = pickerPercentDec,
                onValueChange = { pickerPercentDec = it },
                dividersColor = MaterialTheme.colors.secondary,
                range = 0..9
            )
            Text("lbs") // CHANGE WITH SETTINGS
        }
    }
}

@Composable
fun WeighInDiet() {
    // TEMP STATE HOLDERS
    var calorieCount by remember { mutableStateOf(1543.0) }
    var calorieNew by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calories: ${ calorieCount.toInt() }",
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.h4
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val focusManager = LocalFocusManager.current

            TextField(
                value = calorieNew,
                onValueChange = { calorieNew = it },
                label = { Text("Add") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            try {
                                calorieCount += calorieNew.toFloat().absoluteValue
                            } catch (ex: NumberFormatException) {
                                Log.d(
                                    "WEIGH IN",
                                    "Diet entry not a number: ${ ex.localizedMessage }"
                                )
                            }
                            calorieNew = ""
                        }
                    )
                },
                isError = "[^0-9]".toRegex().containsMatchIn(calorieNew),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions { focusManager.clearFocus() },
                singleLine = true,
                shape = MaterialTheme.shapes.small
            )
        }
    }
}

@Composable
fun WeighInMeasurements() {
    // TEMP STATE HOLDERS
    var upperArmLeft by remember { mutableStateOf("18.5") }
    var upperArmRight by remember { mutableStateOf("18.5") }
    var forearmLeft by remember { mutableStateOf("18.5") }
    var forearmRight by remember { mutableStateOf("18.5") }
    var chest by remember { mutableStateOf("54.0") }
    var thighLeft by remember { mutableStateOf("27.0") }
    var thighRight by remember { mutableStateOf("27.0") }
    var calfLeft by remember { mutableStateOf("18.5") }
    var calfRight by remember { mutableStateOf("18.5") }
    var waist by remember { mutableStateOf("30.0") }
    var shoulder by remember { mutableStateOf("18.5") }

    val scrollState = rememberScrollState()
    val numberRegex = """^\d+(\.\d+)?$""".toRegex()

    Column(
        modifier = Modifier
            .padding(8.dp)
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        val focusManager = LocalFocusManager.current

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Shoulder",
                modifier = Modifier.fillMaxWidth(0.33f),
                textAlign = TextAlign.Left
            )
            TextField(
                value = shoulder,
                onValueChange = { shoulder = it },
                modifier = Modifier.width(96.dp),
//                label = { Text("left") },
                isError = !numberRegex.containsMatchIn(shoulder),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                ),
                singleLine = true,
                shape = MaterialTheme.shapes.small
            )
            Spacer(Modifier.width(96.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Upper Arm",
                modifier = Modifier.fillMaxWidth(0.33f),
                textAlign = TextAlign.Left
            )
            TextField(
                value = upperArmLeft,
                onValueChange = { upperArmLeft = it },
                modifier = Modifier.width(96.dp),
                label = { Text("left") },
                isError = !numberRegex.containsMatchIn(upperArmLeft),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Right) }
                ),
                singleLine = true,
                shape = MaterialTheme.shapes.small
            )
            TextField(
                value = upperArmRight,
                onValueChange = { upperArmRight = it },
                modifier = Modifier.width(96.dp),
                label = { Text("right") },
                isError = !numberRegex.containsMatchIn(upperArmRight),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                ),
                singleLine = true,
                shape = MaterialTheme.shapes.small
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Forearm",
                modifier = Modifier.fillMaxWidth(0.33f),
                textAlign = TextAlign.Left
            )
            TextField(
                value = forearmLeft,
                onValueChange = { forearmLeft = it },
                modifier = Modifier.width(96.dp),
                label = { Text("left") },
                isError = !numberRegex.containsMatchIn(forearmLeft),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Right) }
                ),
                singleLine = true,
                shape = MaterialTheme.shapes.small
            )
            TextField(
                value = forearmRight,
                onValueChange = { forearmRight = it },
                modifier = Modifier.width(96.dp),
                label = { Text("right") },
                isError = !numberRegex.containsMatchIn(forearmRight),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                ),
                singleLine = true,
                shape = MaterialTheme.shapes.small
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Chest",
                modifier = Modifier.fillMaxWidth(0.33f),
                textAlign = TextAlign.Left
            )
            TextField(
                value = chest,
                onValueChange = { chest = it },
                modifier = Modifier.width(96.dp),
//                label = { Text("left") },
                isError = !numberRegex.containsMatchIn(chest),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                singleLine = true,
                shape = MaterialTheme.shapes.small
            )
            Spacer(Modifier.width(96.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Waist",
                modifier = Modifier.fillMaxWidth(0.33f),
                textAlign = TextAlign.Left
            )
            TextField(
                value = waist,
                onValueChange = { waist = it },
                modifier = Modifier.width(96.dp),
//                label = { Text("left") },
                isError = !numberRegex.containsMatchIn(waist),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                singleLine = true,
                shape = MaterialTheme.shapes.small
            )
            Spacer(Modifier.width(96.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Thigh",
                modifier = Modifier.fillMaxWidth(0.33f),
                textAlign = TextAlign.Left
            )
            TextField(
                value = thighLeft,
                onValueChange = { thighLeft = it },
                modifier = Modifier.width(96.dp),
                label = { Text("left") },
                isError = !numberRegex.containsMatchIn(thighLeft),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Right) }
                ),
                singleLine = true,
                shape = MaterialTheme.shapes.small
            )
            TextField(
                value = thighRight,
                onValueChange = { thighRight = it },
                modifier = Modifier.width(96.dp),
                label = { Text("right") },
                isError = !numberRegex.containsMatchIn(thighRight),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                ),
                singleLine = true,
                shape = MaterialTheme.shapes.small
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Calves",
                modifier = Modifier.fillMaxWidth(0.33f),
                textAlign = TextAlign.Left
            )
            TextField(
                value = calfLeft,
                onValueChange = { calfLeft = it },
                modifier = Modifier.width(96.dp),
                label = { Text("left") },
                isError = !numberRegex.containsMatchIn(calfLeft),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Right) }
                ),
                singleLine = true,
                shape = MaterialTheme.shapes.small
            )
            TextField(
                value = calfRight,
                onValueChange = { calfRight = it },
                modifier = Modifier.width(96.dp),
                label = { Text("right") },
                isError = !numberRegex.containsMatchIn(calfRight),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                singleLine = true,
                shape = MaterialTheme.shapes.small
            )
        }
    }
}

@Composable
fun WeighInBodyFat() {
    // TEMP STATE HOLDERS
    var pickerPercentInt by remember { mutableStateOf(20) }
    var pickerPercentDec by remember { mutableStateOf(5) }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Body Fat Percentage",
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.h4
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumberPicker(
                value = pickerPercentInt,
                onValueChange = { pickerPercentInt = it },
                dividersColor = MaterialTheme.colors.secondary,
                range = 0..99
            )
            Text(".")
            NumberPicker(
                value = pickerPercentDec,
                onValueChange = { pickerPercentDec = it },
                dividersColor = MaterialTheme.colors.secondary,
                range = 0..9
            )
        }
    }
}

@Preview
@Composable
fun PreviewWeighInWeight() {
    HyperTrophyTheme {
        WeighInWeight()
    }
}

@Preview
@Composable
fun PreviewWeighInDiet() {
    HyperTrophyTheme {
        WeighInDiet()
    }
}

@Preview
@Composable
fun PreviewWeighInMeasurements() {
    HyperTrophyTheme {
        WeighInMeasurements()
    }
}

@Preview
@Composable
fun PreviewWeighInBodyFat() {
    HyperTrophyTheme {
        WeighInBodyFat()
    }
}