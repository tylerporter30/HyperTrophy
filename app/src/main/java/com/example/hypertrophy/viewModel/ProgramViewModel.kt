package com.example.hypertrophy.viewModel

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.hypertrophy.database.Program
import com.example.hypertrophy.database.ProgramConverter
import com.example.hypertrophy.database.ProgramDatabase
import kotlinx.coroutines.launch


class ProgramViewModel(appObj: Application) : AndroidViewModel(appObj) {

    var listOfProgram: MutableState<List<Program>> = mutableStateOf(listOf())

    val programDB = ProgramDatabase.getDataBase(appObj)
    var programDao = programDB.exerciseDao()

    fun fetchAllProgram(){
        viewModelScope.launch {
            listOfProgram .value = programDao.fetchAllProgram()
        }
    }

    fun insertProgram(program: Program){
        viewModelScope.launch {
            programDao.insertProgram(program = program)
        }
    }

    fun deleteProgramByName(name:String){
        viewModelScope.launch {
            programDao.deleteProgramByName(name)
        }
    }
}