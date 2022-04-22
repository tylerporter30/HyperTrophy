package com.example.hypertrophy.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hypertrophy.data.HistoryRecord
import com.example.hypertrophy.data.PersonalWeightInRecord
import com.example.hypertrophy.data.Program

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM program")
    fun fetchAllProgram(): LiveData<List<Program>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgram(program: Program)
    @Query("SELECT * FROM program where name = :name")
    suspend fun fetchProgramByName(name:String):List<Program>
    @Query("delete FROM program WHERE name = :name")
    suspend fun deleteProgramByName(name:String)

    //For Personal weight in records
    @Query("SELECT * FROM personalWeightInRecord")
    fun fetchAllPersonalWeightInRecord(): LiveData<List<PersonalWeightInRecord>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPersonalWeightInRecord(personalWeightInRecord: PersonalWeightInRecord)
    @Query("SELECT * FROM personalWeightInRecord where date = :date")
    suspend fun fetchPersonalWeightInRecordByDate(date:String):List<PersonalWeightInRecord>
    @Query("delete FROM personalWeightInRecord WHERE date = :date")
    suspend fun deletePersonalWeightInRecordByDate(date:String)

    //For History record
    @Query("SELECT * FROM historyRecord")
    fun fetchAllHistoryRecord(): LiveData<List<HistoryRecord>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoryRecord(historyRecord: HistoryRecord)
    @Query("SELECT * FROM historyRecord where date = :date")
    suspend fun fetchHistoryRecordByDate(date:String):List<HistoryRecord>
    @Query("delete FROM historyRecord WHERE date = :date")
    suspend fun deleteHistoryRecordByDate(date:String)

}