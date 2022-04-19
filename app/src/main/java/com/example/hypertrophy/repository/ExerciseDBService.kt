package com.example.hypertrophy.model.repository

import com.example.hypertrophy.data.ExerciseInfo
import retrofit2.Response
import retrofit2.http.GET

interface ExerciseDBService {

    @GET("exercises")
    suspend fun fetchAllExercises():Response<List<ExerciseInfo>>

    @GET("exercises/bodyPartList")
    suspend fun fetchBodyPartList():Response<List<String>>

}