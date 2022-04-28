package com.example.hypertrophy

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hypertrophy.data.Program
import com.example.hypertrophy.data.Template
import com.example.hypertrophy.viewModel.ProgramViewModel

@Composable
fun CreateNewProgram(navController: NavHostController, programViewModel: ProgramViewModel) {

    Scaffold(
        topBar = {
            TopAppBar(title = {
                IconButton(onClick = { navController.navigate(NavRoutes.Programs.route) }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Create New Program"
                    )
                }

                Text(text = "Create New Program")
            })
        },

        content = { CreateNewProgramTextField(navController = navController, programViewModel) },

        //bottomBar = { BottomBarNavigation(navController = navController) }
    )
}

@Composable
fun CreateNewProgramTextField(
    navController: NavHostController,
    programViewModel: ProgramViewModel
) {

    var text by remember { mutableStateOf("") }
    val programs by programViewModel.listOfProgram.observeAsState(listOf())

    Column(
        modifier = Modifier
            .fillMaxSize(),
        Arrangement.Center
    ) {

        Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
            Text(text = "Create Program Name", style = MaterialTheme.typography.h3)
        }

        TextField(
            value = text,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            onValueChange = { text = it }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .border(
                    1.dp,
                    color = MaterialTheme.colors.secondary,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(10.dp)
        ) {

            item {
                Text(text = "Existing Programs")
            }

            items(programs) {

                Card(
                    modifier = Modifier
                        .border(
                            1.dp,
                            color = MaterialTheme.colors.secondary,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(16.dp)
                        .fillMaxWidth(),
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "${it.name}")

                        Text(
                            modifier = Modifier.clickable { programViewModel.deleteProgramByName(it.name) },
                            text = "Delete",
                            color = Color.Red
                        )

                    }
                }
            }
        }
    }

    FloatingActionButtonComponent2(
        navController = navController,
        TemplateName = text,
        programViewModel
    )
}

@Composable
fun FloatingActionButtonComponent2(
    navController: NavHostController,
    TemplateName: String,
    programViewModel: ProgramViewModel
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp, end = 10.dp), verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {


        FloatingActionButton(
            onClick = {

                programViewModel.currentProgram = Program(TemplateName, listOf())
                programViewModel.currentTemplate =
                    mutableStateOf(Template("", listOfExercise = listOf()))
                navController.navigate(NavRoutes.CreateNewTemplate.route)
            },

            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            backgroundColor = MaterialTheme.colors.primary
//            backgroundColor = Color.Black
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                tint = Color.White, contentDescription = "Floating Button",
                modifier = Modifier.size(35.dp)

            )
        }
    }
}