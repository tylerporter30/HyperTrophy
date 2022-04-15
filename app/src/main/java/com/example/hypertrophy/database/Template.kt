package com.example.hypertrophy.database

data class Template(

    var templateName:String,
    var listOfExercise :List<Exercise> = listOf()
)
