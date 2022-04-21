package com.example.hypertrophy.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hypertrophy.data.*
import com.example.hypertrophy.viewModel.ProgramViewModel


@Composable
fun Screen_History(programViewModel: ProgramViewModel){

    var listOfHistoryRecord = programViewModel.listOfHistoryRecord.observeAsState(listOf())

    Scaffold {

        LazyColumn(modifier = Modifier.fillMaxSize().padding(15.dp)){
            
            items(listOfHistoryRecord.value){

                Card(elevation = 15.dp, modifier = Modifier.padding(15.dp)){
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(15.dp)) {

                        Text(text = it.date,style = MaterialTheme.typography.h6)
                        TemplateOfHistory(templates = it.templates)
                    }

                }
            }
        }
    }
}

@Composable
fun HistoryCard(templates:List<Template>){


}


@Composable
fun TemplateOfHistory(templates:List<Template>){

    LazyColumn(modifier = Modifier.height(200.dp).fillMaxWidth()){

        items(templates){

            Text(text = it.templateName)
            ExerciseOfTemplate(it.listOfExercise)
        }
    }
}



@Composable
fun ExerciseOfTemplate(listOfExercise: List<Exercise>){

    LazyColumn(modifier = Modifier.height(100.dp)){

        items(listOfExercise){

            Text(text = it.exerciseInfo.name)
            SetOfExercise(listOfSets = it.listOfSets)
        }
    }
}

@Composable
fun SetOfExercise(listOfSets : List<Sets>){

    LazyColumn(modifier = Modifier.height(50.dp)) {

        items(listOfSets){

            Row {
                Text(text = "Set ${it.setNum}")
                Text(text = "Weight: ${it.Weight}")
                Text(text = "RPS: ${it.rpe}")
                Text(text = "resp: ${it.reps}")
            }
        }
    }
}

@Composable
fun testHistory(programViewModel:ProgramViewModel){
    var set1 = Sets(1,160,1,1)
    var set2 = Sets(2,140,2,2)
    var exer1 = Exercise(ExerciseInfo("abs","bodyweight","sdf","af","sit up","abs"), listOf(set1,set2))
    var exer2 = Exercise(ExerciseInfo("bicep","dumbbell","sdf","af","test","bicep"), listOf(set1,set2))
    var template = Template("temp1", listOf(exer1,exer2))
    var template1 = Template("temp2", listOf(exer1,exer2))
    var program = Program(name="program1", templates = listOf(template,template1))
    programViewModel.insertHistoryRecord(HistoryRecord("4/21/22", listOf(template,template1)))
    programViewModel.insertHistoryRecord(HistoryRecord("4/25/22", listOf(template,template1)))
    programViewModel.insertHistoryRecord(HistoryRecord("4/26/22", listOf(template,template1)))


    Screen_History(programViewModel = programViewModel)
}




