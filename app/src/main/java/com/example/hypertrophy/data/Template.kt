package com.example.hypertrophy.data

import com.example.hypertrophy.data.Exercise

data class Template(

    var templateName:String,
    var listOfExercise :List<Exercise> = listOf()
)