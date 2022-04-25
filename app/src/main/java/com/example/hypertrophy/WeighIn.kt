package com.example.hypertrophy

import android.app.DatePickerDialog
import android.widget.DatePicker
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.hypertrophy.viewModel.WeighInViewModel

@Composable
fun WeighInScreen(
    navController: NavHostController,
    viewModel: WeighInViewModel
) {
    Scaffold(
        topBar = { TopAppBar(title = {
            IconButton(onClick = { navController.navigate(NavRoutes.Home.route) }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
            }
            Text(text = "Weigh In") }) },
        content = {
            Box(Modifier.padding(it)) {
                WeighInScreenUI(viewModel)
            }
        },
        bottomBar = { BottomBarNavigation(navController = navController) }
    )
}

// @ExperimentalPagerApi
@Composable
fun WeighInScreenUI(viewModel: WeighInViewModel) {
    var tabIndex by remember { mutableStateOf(0) }
    val body1 = MaterialTheme.typography.body1
    var textStyle by remember { mutableStateOf(body1) }
    var readyToDraw by remember { mutableStateOf(false) }
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
                    text = {
                        Text(
                            text = title,
                            modifier = Modifier.drawWithContent { if (readyToDraw) drawContent() },
                            maxLines = 1,
                            softWrap = false,
                            style = textStyle,
                            onTextLayout = {
                                if (it.didOverflowWidth) {
                                    textStyle = textStyle.copy(fontSize = textStyle.fontSize * 0.9f)
                                } else {
                                    readyToDraw = true
                                }
                            }
                        )
                    },
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
//                0 -> WeighInWeight(viewModel)
//                1 -> WeighInDiet(viewModel)
//                2 -> WeighInMeasurements(viewModel)
//                3 -> WeighInBodyFat(viewModel)
//                else -> WeighInWeight(viewModel)
//            }
//        }
        when (tabIndex) {
            0 -> WeighInWeight(viewModel)
            1 -> WeighInDiet(viewModel)
            2 -> WeighInMeasurements(viewModel)
            3 -> WeighInBodyFat(viewModel)
            else -> WeighInWeight(viewModel)
        }
    }
}

@Composable
fun DateSelection(viewModel: WeighInViewModel) {
    val dateSelected by viewModel.dateSelectedLive.observeAsState()
    val datePicker = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            viewModel.updateDateSelected(mYear, mMonth, mDayOfMonth)
        },
        viewModel.dateToday[0],
        viewModel.dateToday[1],
        viewModel.dateToday[2]
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Current date selected: $dateSelected\nTap to change date",
            modifier = Modifier
                .clickable { datePicker.show() }
                .padding(8.dp)
                .alpha(0.6f),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Composable
fun WeighInWeight(viewModel: WeighInViewModel) {
    val pickerPercentInt by viewModel.weightPickerIntLive.observeAsState(100)
    val pickerPercentDec by viewModel.weightPickerDecLive.observeAsState(0)

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
                onValueChange = { viewModel.updateWeightPickerInt(it) },
                dividersColor = MaterialTheme.colors.secondary,
                range = 0..999
            )
            Text(".")
            NumberPicker(
                value = pickerPercentDec,
                onValueChange = { viewModel.updateWeightPickerDec(it) },
                dividersColor = MaterialTheme.colors.secondary,
                range = 0..9
            )
            Text("lbs") // TODO() CHANGE WITH SETTINGS
        }
        DateSelection(viewModel)
        Button(
            onClick = { viewModel.saveRecord() },
            modifier = Modifier.padding(16.dp),
            enabled = viewModel.enableSaveWeightButton(),
            contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "Save", style = MaterialTheme.typography.button)
        }
    }
}

@Composable
fun WeighInDiet(viewModel: WeighInViewModel) {
    val calorieCount by viewModel.calorieCountTodayLive.observeAsState(0.0f)
    val calorieNew by viewModel.calorieCountNowLive.observeAsState("")

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
                onValueChange = { viewModel.updateCalorieNow(it) },
                label = { Text("Add") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            viewModel.updateCalorieCount(calorieNew)
                            viewModel.updateCalorieNow("")
                        }
                    )
                },
                isError = """[^0-9]""".toRegex().containsMatchIn(calorieNew),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions { focusManager.clearFocus() },
                singleLine = true,
                shape = MaterialTheme.shapes.small
            )
        }
        DateSelection(viewModel)
        Button(
            onClick = { viewModel.saveRecord() },
            modifier = Modifier.padding(16.dp),
            enabled = viewModel.enableSaveDietButton(),
            contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "Save", style = MaterialTheme.typography.button)
        }
    }
}

@Composable
fun WeighInMeasurements(viewModel: WeighInViewModel) {
    val shoulder by viewModel.measureShoulderLive.observeAsState("")
    val upperArmLeft by viewModel.measureUpperArmLeftLive.observeAsState("")
    val upperArmRight by viewModel.measureUpperArmRightLive.observeAsState("")
    val forearmLeft by viewModel.measureForearmLeftLive.observeAsState("")
    val forearmRight by viewModel.measureForearmRightLive.observeAsState("")
    val chest by viewModel.measureChestLive.observeAsState("")
    val waist by viewModel.measureWaistLive.observeAsState("")
    val thighLeft by viewModel.measureThighLeftLive.observeAsState("")
    val thighRight by viewModel.measureThighRightLive.observeAsState("")
    val calfLeft by viewModel.measureCalfLive.observeAsState("")
    val calfRight by viewModel.measureCalfRightLive.observeAsState("")

    val scrollState = rememberScrollState()
    val numberRegex = """^\d+(\.\d+)?$""".toRegex()

    Column(
        modifier = Modifier.verticalScroll(state = scrollState),
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
            MeasurementField(
                value = shoulder,
                onValueChange = { viewModel.updateShoulder(it) },
                regex = numberRegex,
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
            MeasurementField(
                value = upperArmLeft,
                onValueChange = { viewModel.updateUpperArmLeft(it) },
                regex = numberRegex,
                label = "left"
            )
            MeasurementField(
                value = upperArmRight,
                onValueChange = { viewModel.updateUpperArmRight(it) },
                regex = numberRegex,
                label = "right"
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
            MeasurementField(
                value = forearmLeft,
                onValueChange = { viewModel.updateForearmLeft(it) },
                regex = numberRegex,
                label = "left"
            )
            MeasurementField(
                value = forearmRight,
                onValueChange = { viewModel.updateForearmRight(it) },
                regex = numberRegex,
                label = "right"
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
            MeasurementField(
                value = chest,
                onValueChange = { viewModel.updateChest(it) },
                regex = numberRegex,
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
            MeasurementField(
                value = waist,
                onValueChange = { viewModel.updateWaist(it) },
                regex = numberRegex,
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
            MeasurementField(
                value = thighLeft,
                onValueChange = { viewModel.updateThighLeft(it) },
                regex = numberRegex,
                label = "left"
            )
            MeasurementField(
                value = thighRight,
                onValueChange = { viewModel.updateThighRight(it) },
                regex = numberRegex,
                label = "right"
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
            MeasurementField(
                value = calfLeft,
                onValueChange = { viewModel.updateCalfLeft(it) },
                regex = numberRegex,
                label = "left"
            )
            MeasurementField(
                value = calfRight,
                onValueChange = { viewModel.updateCalfRight(it) },
                regex = numberRegex,
                imeAction = ImeAction.Done,
                label = "right"
            )
        }
        DateSelection(viewModel)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { viewModel.saveRecord() },
                modifier = Modifier.padding(16.dp),
                enabled = viewModel.enableSaveMeasurementsButton(),
                contentPadding = PaddingValues(8.dp)
            ) {
                Text(text = "Save", style = MaterialTheme.typography.button)
            }
        }
        Spacer(Modifier.height(128.dp))
    }
}

@Composable
fun MeasurementField(
    value: String,
    onValueChange: (String) -> Unit,
    regex: Regex,
    imeAction: ImeAction = ImeAction.Next,
    label: String = "",
) {
    val focusManager = LocalFocusManager.current

    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier.width(96.dp),
        label = { Text(label) },
        isError = !regex.containsMatchIn(value),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        ),
        singleLine = true,
        shape = MaterialTheme.shapes.small
    )
}

@Composable
fun WeighInBodyFat(viewModel: WeighInViewModel) {
    val pickerPercentInt by viewModel.bodyFatPickerIntLive.observeAsState(0)
    val pickerPercentDec by viewModel.bodyFatPickerDecLive.observeAsState(0)

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
                onValueChange = { viewModel.updateBodyFatPickerInt(it) },
                dividersColor = MaterialTheme.colors.secondary,
                range = 0..99
            )
            Text(".")
            NumberPicker(
                value = pickerPercentDec,
                onValueChange = { viewModel.updateBodyFatPickerDec(it) },
                dividersColor = MaterialTheme.colors.secondary,
                range = 0..9
            )
        }
        DateSelection(viewModel)
        Button(
            onClick = { viewModel.saveRecord() },
            modifier = Modifier.padding(16.dp),
            enabled = viewModel.enableSaveBodyFatButton(),
            contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "Save", style = MaterialTheme.typography.button)
        }
    }
}