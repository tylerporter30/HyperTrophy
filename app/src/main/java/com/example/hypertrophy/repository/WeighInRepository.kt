package com.example.hypertrophy.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.hypertrophy.data.PersonalWeightInRecord
import com.example.hypertrophy.database.ExerciseDao
import com.example.hypertrophy.database.ProgramDatabase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

@ViewModelScoped
class WeighInRepository @Inject constructor(private val weighInDao: ExerciseDao) {

//    @Inject lateinit var weighInDao: ExerciseDao

//    init {
//        val database = ProgramDatabase.getDataBase(application)
//        weighInDao = database.exerciseDao()
//    }

    val allWeightInRecord: LiveData<List<PersonalWeightInRecord>> =
        weighInDao.fetchAllPersonalWeightInRecord()

    suspend fun isPersonalWeightInEmpty(): Boolean {
        return weighInDao.isPersonalWeightInEmpty()
    }

    suspend fun insertPersonalWeightInRecord(personalWeightInRecord: PersonalWeightInRecord) {
        weighInDao.insertPersonalWeightInRecord(personalWeightInRecord)
    }

    suspend fun fetchPersonalWeightInRecordByDate(date: String): List<PersonalWeightInRecord> {
        return weighInDao.fetchPersonalWeightInRecordByDate(date)
    }

    suspend fun fetchPersonalWeightInRecordByLast(): List<PersonalWeightInRecord> {
        return weighInDao.fetchPersonalWeightInRecordByLast()
    }

    suspend fun deletePersonalWeightInRecordByDate(date:String) {
        weighInDao.deletePersonalWeightInRecordByDate(date)
    }
}