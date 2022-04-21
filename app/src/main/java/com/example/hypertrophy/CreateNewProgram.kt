package com.example.hypertrophy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hypertrophy.data.Template

@Composable
fun CreateNewProgram(navController: NavHostController) {

    Scaffold(
        topBar = { TopAppBar( title = {
            IconButton(onClick = { navController.navigate(NavRoutes.Programs.route) }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Create New Program")
            }

            Text(text = "Create New Program") }) },

        content = { CreateNewProgramTextField(navController = navController)},

        //bottomBar = { BottomBarNavigation(navController = navController) }
    )
}

@Composable
fun CreateNewProgramTextField(navController: NavHostController) {

    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        Arrangement.Center) {

        Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {


            Text(text = "Create Program Name")
        }


        TextField(
            value = text,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            onValueChange = { text = it }
        )

    }

    FloatingActionButtonComponent2(navController = navController, TemplateName = text)
}

@Composable
fun FloatingActionButtonComponent2(navController: NavHostController, TemplateName: String) {

    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 70.dp, end = 10.dp), verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End) {


        FloatingActionButton(
            onClick = {

                navController.navigate(NavRoutes.CreateNewTemplate.route)
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