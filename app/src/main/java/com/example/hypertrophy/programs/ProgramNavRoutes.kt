package com.example.hypertrophy.programs

sealed class ProgramNavRoutes(val route: String) {
    object StartingStrengthWorkoutA: ProgramNavRoutes("StartingStrengthWorkoutA")
    object StartingStrengthWorkoutB: ProgramNavRoutes("StartingStrengthWorkoutB")
}
