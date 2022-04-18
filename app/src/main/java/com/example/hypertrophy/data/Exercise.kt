package com.example.hypertrophy.data

import com.example.hypertrophy.model.network.ExerciseInfo

data class Exercise(

    var exerciseInfo: ExerciseInfo,
    var listOfSets:List<Sets> = listOf()
)
