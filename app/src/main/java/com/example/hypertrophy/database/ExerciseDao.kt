package com.example.hypertrophy.database

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM program")
    fun fetchAllProgram(): List<Program>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgram(program: Program)

    @Query("delete FROM program WHERE name = :name")
    suspend fun deleteProgramByName(name:String)

}