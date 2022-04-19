package com.example.hypertrophy.data

data class Exercise(

    var exerciseInfo: ExerciseInfo,
    var listOfSets:List<Sets> = listOf()
)