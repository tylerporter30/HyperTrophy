package com.example.hypertrophy.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import com.example.hypertrophy.NavRoutes
import com.example.hypertrophy.R
import com.example.hypertrophy.data.Exercise
import com.example.hypertrophy.data.ExerciseInfo
import com.example.hypertrophy.data.Template
import com.example.hypertrophy.viewModel.ExercisesViewModel
import com.example.hypertrophy.viewModel.ProgramViewModel

@Composable
fun Screen_BrowseAllExercise(navController: NavHostController, exercisesViewModel: ExercisesViewModel, programViewModel: ProgramViewModel) {

    var tabIndex by remember { mutableStateOf(0) }

    Scaffold {

        Column {

            ScrollableTabRow(selectedTabIndex = tabIndex) {
                exercisesViewModel.bodyPartList.forEachIndexed { index, title ->
                    Tab(selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        text = { Text(text = title) })
                }
            }
            TabContent(navController = navController,tabIndex, exercisesViewModel, programViewModel = programViewModel)
        }
    }
}

@Composable
fun TabContent(navController: NavHostController,tabIndex: Int, exercisesViewModel: ExercisesViewModel,programViewModel: ProgramViewModel) {

    var typeExpanded by remember { mutableStateOf(false) }

//    var targetedMuscle by rememberSaveable{ mutableStateOf("")}

    Column {

        Row(horizontalArrangement = Arrangement.End) {

            DropdownMenu(
                expanded = typeExpanded,
                onDismissRequest = { typeExpanded = false },
                modifier = Modifier,
            ) {}
        }

        LazyColumn() {

            items(exercisesViewModel.exerciseList.value.filter { it.bodyPart == exercisesViewModel.bodyPartList[tabIndex] /*&& targetedMuscle in it.target*/ }) {

                    it ->
                ExerciseInfoCard(navController = navController, exerciseInfo = it, programViewModel = programViewModel)
            }
        }
    }
}


@ExperimentalCoilApi
@Composable
fun ExerciseInfoCard(navController: NavHostController,exerciseInfo: ExerciseInfo,programViewModel: ProgramViewModel) {

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Image(
                modifier = Modifier
                    .size(150.dp),
                painter = rememberImagePainter(
                    data = exerciseInfo.gifUrl.substring(
                        0,
                        4
                    ) + "s" + exerciseInfo.gifUrl.substring(4),
                    builder = {
                        decoder(GifDecoder()) // gif decoder
                        placeholder(R.drawable.ic_launcher_foreground)
                        crossfade(true)
                    }),
                contentDescription = null
            )

            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {

                Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {

                    Text(text = exerciseInfo.name, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(5.dp))

                Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Targeted Body Part: ${exerciseInfo.bodyPart}")
                    Text(text = "Targeted Muscle: ${exerciseInfo.target}")
                    Text(text = "Equipment: ${exerciseInfo.equipment}")
                }

                Button(onClick = {
                    programViewModel.currentTemplate.value = Template(programViewModel.currentTemplate.value.templateName,programViewModel.currentTemplate.value.listOfExercise.plus(
                        Exercise(exerciseInfo)
                    ))
                    navController.navigate(NavRoutes.CreateNewTemplate.route)
                     }) {
                    Text(text = "Add to Template")
                }
            }
        }
    }
}
