package com.example.hypertrophy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hypertrophy.database.Template

@Composable
fun CreateNewTemplate(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(
            title = {
                IconButton(onClick = { navController.navigate(NavRoutes.CreateNewProgram.route) }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Arrow")
                }
                Text(text = "Create New Template")
            }
        ) },
        content = { CreateWorkoutTemplateScreen(navController = navController) }
    )
}

@Composable
fun CreateWorkoutTemplateScreen(navController: NavHostController) {
    Column(

    ) {
        val focusManager = LocalFocusManager.current

        //Add Title
        var TemplateTitle by rememberSaveable {mutableStateOf("")}
        //var ListOfExercises by rememberSaveable { mutableStateOf(List<Exercise>())}

        //This should create a new instance of Template class, with a name a list of exercises
        // as parameters
        var newTemplate = Template(templateName = TemplateTitle, listOfExercise = listOf())

        OutlinedTextField(
            value = TemplateTitle,
            onValueChange = { text: String ->
                TemplateTitle = text
            },
            label = {
                    Text(text = "Enter Title")
            },
            singleLine = true,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            visualTransformation = VisualTransformation.None,
            keyboardOptions = KeyboardOptions.Default
        )

        //Card to Display Template
        Card(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            elevation = 15.dp,
            shape = RoundedCornerShape(10.dp)
            ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = TemplateTitle)
                }

            }
        }

        //Add Exercise
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { navController.navigate(NavRoutes.Browse.route) }) {
                Text(text = "Add Exercise")
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(onClick = {  }) {
                Text(text = "Save Template")
            }
        }
    }
}

