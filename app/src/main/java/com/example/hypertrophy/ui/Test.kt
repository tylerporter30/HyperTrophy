package com.example.hypertrophy.ui

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.hypertrophy.data.Exercise
import com.example.hypertrophy.data.Program
import com.example.hypertrophy.data.Sets
import com.example.hypertrophy.data.Template
import com.example.hypertrophy.data.ExerciseInfo
import com.example.hypertrophy.viewModel.ProgramViewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.hypertrophy.data.PersonalWeightInRecord


@Composable
fun test(viewModel: ProgramViewModel){

    var set1 = Sets(1,160,1,1)
    var set2 = Sets(2,140,2,2)
    var exer1 = Exercise(ExerciseInfo("abs","bodyweight","sdf","af","sit up","abs"), listOf(set1,set2))
    var exer2 = Exercise(ExerciseInfo("bicep","dumbbell","sdf","af","test","bicep"), listOf(set1,set2))
    var template = Template("temp1", listOf(exer1,exer2))
    var template1 = Template("temp2", listOf(exer1,exer2))
    var program = Program(name="program1", templates = listOf(template,template1))

    viewModel.insertProgram(program)

    var li = viewModel.listOfProgram.observeAsState(listOf())
    Log.d("gale",li.value.toString())
    Log.d("galet", li.value.getOrElse(0,{ Program(name = "test", templates = listOf()) }).name.toString())
    viewModel.insertProgram(Program("p2", listOf()))

    var lis = viewModel.listOfPersonalWeightInRecord.observeAsState(listOf())
    Log.d("galep",lis.value.toString())

    viewModel.insertPersonalWeightInRecord(PersonalWeightInRecord("testdate",1f,1,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f))
    viewModel.insertPersonalWeightInRecord(PersonalWeightInRecord("testdate",2f,1,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f))

}