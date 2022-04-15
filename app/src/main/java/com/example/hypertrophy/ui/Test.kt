package com.example.hypertrophy.ui

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.hypertrophy.database.Exercise
import com.example.hypertrophy.database.Program
import com.example.hypertrophy.database.Sets
import com.example.hypertrophy.database.Template
import com.example.hypertrophy.model.network.ExerciseInfo
import com.example.hypertrophy.viewModel.ProgramViewModel


@Composable
fun test(viewModel: ProgramViewModel){

    var set1 = Sets(1,1,1,1)
    var set2 = Sets(2,2,2,2)
    var exer1 = Exercise(ExerciseInfo("adf","adf","sdf","af","f","df"), listOf(set1,set2))
    var exer2 = Exercise(ExerciseInfo("adf","adf","sdf","af","f","df"), listOf(set1,set2))
    var template = Template("temp1", listOf(exer1,exer2))
    var template1 = Template("temp2", listOf(exer1,exer2))
    var program = Program(name="p1", templates = listOf(template,template1))

    viewModel.insertProgram(program)

    viewModel.fetchAllProgram()

    var li = viewModel.listOfProgram
    Log.d("gale",li.value.toString())
    viewModel.deleteProgramByName("p1")
}