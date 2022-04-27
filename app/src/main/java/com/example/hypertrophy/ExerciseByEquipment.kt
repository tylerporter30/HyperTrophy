package com.example.hypertrophy

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
import com.example.hypertrophy.R
import com.example.hypertrophy.data.ExerciseInfo
import com.example.hypertrophy.viewModel.ExercisesViewModel


@Composable
fun ExerciseByEquipment(exercisesViewModel: ExercisesViewModel, navController: NavHostController) {

    var tabIndex by remember { mutableStateOf(0) }

    Scaffold {

        Column {

            ScrollableTabRow(selectedTabIndex = tabIndex) {
                exercisesViewModel.equipmentList.forEachIndexed { index, title ->
                    Tab(selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        text = { Text(text = title) })
                }
            }
            TabContent(tabIndex, exercisesViewModel)
        }
    }
}

@Composable
fun TabContent(tabIndex: Int, exercisesViewModel: ExercisesViewModel) {

    val equipmentList = listOf(
        "assisted",
        "band",
        "barbell",
        "body Weight",
        "bOSU Ball",
        "cables",
        "dumbbell",
        "elliptical",
        "eZ Bar",
        "hammer",
        "kettlebell",
        "leverage Machine",
        "medicine Ball",
        "olympic Barbell",
        "resistance Band",
        "roller",
        "rope",
        "skierg Machine",
        "sled Machine",
        "smith Machine",
        "stability Ball",
        "stationary Bike",
        "stepmill Machine",
        "tire",
        "trap Bar",
        "upper Body Erometer",
        "weighted",
        "wheel Roller"
    )

    Column {

        Row(horizontalArrangement = Arrangement.End) {

        }

        LazyColumn() {

            items(exercisesViewModel.exerciseList.value.filter {
                it.equipment == exercisesViewModel.equipmentList[tabIndex] && it.equipment in equipmentList }) {
                    it ->
                ExerciseInfoCard(exerciseInfo = it)
            }
        }
    }
}


@ExperimentalCoilApi
@Composable
fun ExerciseInfoCard(exerciseInfo: ExerciseInfo) {

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

            }
        }
    }
}
