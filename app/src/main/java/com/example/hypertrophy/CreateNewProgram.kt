package com.example.hypertrophy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CreateNewProgram(navController: NavHostController) {

    Scaffold(
        topBar = { TopAppBar( title = {
            IconButton(onClick = { navController.navigate(NavRoutes.Programs.route) }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "Create New Program")
            }

            Text(text = "Create New Program") }) },

        content = { CreateNewProgramTextField()},

        bottomBar = { BottomBarNavigation(navController = navController) }
    )
}

@Composable
fun CreateNewProgramTextField() {

    Column(modifier = Modifier.fillMaxSize(), Arrangement.Center) {

        Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {


            Text(text = "Create Program Name")
        }

        var text by remember { mutableStateOf("") }

        TextField(
            value = text,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            onValueChange = { text = it },

            )
    }

    FloatingActionButtonComponent()
}

@Composable
fun FloatingActionButtonComponent() {

    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 70.dp, end = 10.dp), verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End) {


        FloatingActionButton(
            onClick = {

                //navController.navigate(NavRoutes.CreateNewProgram.route)
            },

            modifier = Modifier.size(50.dp), shape = CircleShape, backgroundColor = Color.Black
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                tint = Color.White, contentDescription = "Floating Button",
                modifier = Modifier.size(35.dp)

            )
        }
    }
}