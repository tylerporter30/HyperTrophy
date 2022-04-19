package com.example.hypertrophy

import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun EquipmentMatcherScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar( title = {
            IconButton(onClick = { navController.navigate(NavRoutes.Templates.route) }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Templates")
            }
            Text(text = "Equipment") }) },
        content = { EquipmentCheckbox() },
        //bottomBar = { BottomBarNavigation(navController = navController) }
    )
}

@Composable
fun EquipmentCheckbox() {

    //list of equipment
    val equipmentList = listOf("Assisted", "Band", "Barbell", "Body Weight", "BOSU Ball",
        "Cables", "Dumbbell", "Elliptical", "EZ Bar", "Hammer", "Kettlebell",
        "Leverage Machine", "Medicine Ball", "Olympic Barbell", "Resistance Band"
        , "Roller", "Rope", "Skierg Machine", "Sled Machine", "Smith Machine",
        "Stability Ball", "Stationary Bike", "Stepmill Machine", "Tire", "Trap Bar",
        "Upper Body Erometer", "Weighted", "Wheel Roller")

    Column(
        modifier = Modifier

            .fillMaxWidth()
            .padding(top = 50.dp)
            .size(525.dp)
            .verticalScroll(rememberScrollState())
    )

    {

        equipmentList.forEach { items ->

            Row(modifier = Modifier
                .padding(top = 25.dp, start = 35.dp)
                .border(1.dp, color = Color.Black)
                .width(320.dp)
                .height(60.dp)
                .padding(horizontal = 50.dp),

                verticalAlignment = Alignment.CenterVertically
            ) {
                val isChecked = remember { mutableStateOf(false) }

                Checkbox(
                    checked = isChecked.value,
                    onCheckedChange = { isChecked.value = it },
                    enabled = true,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.LightGray,
                        checkmarkColor = Color.Red
                    )
                )
                Text(modifier = Modifier.padding(start = 10.dp), text = items, fontSize = 18.sp)
            }
        }
    }
    EquipmentButton()
}

@Composable
fun EquipmentButton() {

    var context = LocalContext.current


    Button( shape = RoundedCornerShape(10.dp),
        onClick = {
            //context.startActivity(Intent(context, ExerciseCard::class.java))
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