package com.example.hypertrophy

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.hypertrophy.viewModel.ExercisesViewModel

@Composable
fun EquipmentMatcherScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                IconButton(onClick = { navController.navigate(NavRoutes.Home.route) }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Equipment Matcher"
                    )
                }
                Text(text = "Equipment")
            })
        },
                content = { EquipmentCheckbox(navController = navController, exercisesViewModel = ExercisesViewModel()) },

        //bottomBar = { BottomBarNavigation(navController = navController) }
    )
}

@Composable
fun EquipmentCheckbox(navController: NavHostController, exercisesViewModel: ExercisesViewModel) {

    //list of equipment
    val equipmentList = listOf(
        "Assisted",
        "Band",
        "Barbell",
        "Body Weight",
        "BOSU Ball",
        "Cables",
        "Dumbbell",
        "Elliptical",
        "EZ Bar",
        "Hammer",
        "Kettlebell",
        "Leverage Machine",
        "Medicine Ball",
        "Olympic Barbell",
        "Resistance Band",
        "Roller",
        "Rope",
        "Skierg Machine",
        "Sled Machine",
        "Smith Machine",
        "Stability Ball",
        "Stationary Bike",
        "Stepmill Machine",
        "Tire",
        "Trap Bar",
        "Upper Body Erometer",
        "Weighted",
        "Wheel Roller"
    )


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, start = 35.dp, end = 35.dp)
            .size(525.dp)
            .verticalScroll(rememberScrollState()),
    )
    {
        equipmentList.forEach { items ->
            var isChecked = remember { mutableStateOf(false) }

            val selectedTint = MaterialTheme.colors.primary.copy(alpha = .8f)
            val unSelectedTint = Color.White
            val selectedColor = Color.White
            val unselectedColor = Color.Black
            val backgroundTint = if (isChecked.value) selectedTint else unSelectedTint
            val textColor = if (isChecked.value) selectedColor else unselectedColor
            Row(
                Modifier
                    .padding(bottom = 25.dp)
                    .toggleable(

                        value = isChecked.value,
                        role = Role.Checkbox,
                        onValueChange = { isChecked.value = it }
                    )
                    .background(backgroundTint)
                    .border(1.dp, color = Color.Black)
                    .padding(horizontal = 50.dp)
                    .width(320.dp)
                    .height(60.dp)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Checkbox(checked = isChecked.value,
                    onCheckedChange = null,
                    colors = CheckboxDefaults.colors(
                    checkedColor = Color.White,
                    checkmarkColor = Color.Black))
                Text(items,
                    Modifier
                        .padding(start = 10.dp)
                        .weight(1f),
                    style = MaterialTheme.typography.h6,
                    color = textColor
                )
            }
        }
    }

    EquipmentButton(navController = navController, exercisesViewModel = ExercisesViewModel())
}

@Composable
fun EquipmentButton(navController: NavHostController,exercisesViewModel: ExercisesViewModel) {

    var context = LocalContext.current

    Button(
        shape = RoundedCornerShape(10.dp),
        onClick = {
            //exercisesViewModel.exerciseList.value.filter { true }
            navController.navigate(NavRoutes.ExerciseByEquipment.route)
        },
        modifier = Modifier
            .padding(top = 600.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth()

    ) {
        Text(
            "Submit Equipment",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.W600,
            fontSize = 20.sp,
            modifier = Modifier.padding(1.dp)
        )
    }
}