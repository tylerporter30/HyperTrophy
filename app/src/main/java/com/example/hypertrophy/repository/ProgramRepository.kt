package com.example.hypertrophy.repository

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import com.example.hypertrophy.database.ExerciseDao
import com.example.hypertrophy.database.Program
import com.example.hypertrophy.database.ProgramDatabase

//class ProgramRepository(application: Application) {
//
//    private lateinit var programDao: ExerciseDao
//
//    init {
//
//        var database = ProgramDatabase.getDataBase(application)
//
//        programDao = database.exerciseDao()
//    }
//
//    suspend fun deleteProgramByName(name: String) {
//
//        programDao.deleteProgramByName(name)
//    }
//
//    suspend fun insertProgram(program: Program) {
//
//        programDao.insertProgram(program)
//    }
//}