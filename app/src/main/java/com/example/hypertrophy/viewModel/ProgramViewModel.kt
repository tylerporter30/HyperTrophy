package com.example.hypertrophy.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hypertrophy.data.HistoryRecord
import com.example.hypertrophy.data.PersonalWeightInRecord
import com.example.hypertrophy.database.ExerciseDao
import com.example.hypertrophy.data.Program
import com.example.hypertrophy.database.ProgramDatabase
import kotlinx.coroutines.launch


class ProgramViewModel(appObj: Application) : AndroidViewModel(appObj) {

    var programDao:ExerciseDao

    init {

        val programDB = ProgramDatabase.getDataBase(appObj)
        programDao = programDB.exerciseDao()

    }

    var listOfProgram: LiveData<List<Program>> = programDao.fetchAllProgram()

    var listOfPersonalWeightInRecord:LiveData<List<PersonalWeightInRecord>> = programDao.fetchAllPersonalWeightInRecord()

    var listOfHistoryRecord:LiveData<List<HistoryRecord>> = programDao.fetchAllHistoryRecord()


    //for program
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


    //for personal weight record
    fun insertPersonalWeightInRecord(personalWeightInRecord: PersonalWeightInRecord){
        viewModelScope.launch {
            programDao.insertPersonalWeightInRecord(personalWeightInRecord)
        }
    }
    fun deletePersonalWeightInRecord(date:String){
        viewModelScope.launch {
            programDao.deletePersonalWeightInRecordByDate(date)
        }
    }

    //for history
    fun insertHistoryRecord(historyRecord: HistoryRecord){
        viewModelScope.launch {
            programDao.insertHistoryRecord(historyRecord)
        }
    }
    fun deleteHistoryRecordByDate(date:String){
        viewModelScope.launch {
            programDao.deleteHistoryRecordByDate(date)
        }
    }
}