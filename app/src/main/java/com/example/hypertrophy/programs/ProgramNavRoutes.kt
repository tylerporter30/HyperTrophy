package com.example.hypertrophy.programs

sealed class ProgramNavRoutes(val route: String) {
    object StartingStrengthWorkoutA: ProgramNavRoutes("StartingStrengthWorkoutA")
    object StartingStrengthWorkoutB: ProgramNavRoutes("StartingStrengthWorkoutB")
    object GreySkullWorkoutA: ProgramNavRoutes("GreySkullWorkoutA")
    object GreySkullWorkoutB: ProgramNavRoutes("GreySkullWorkoutB")
    object StrongLiftsWorkoutA: ProgramNavRoutes("StrongLiftsWorkoutA")
    object StrongLiftsWorkoutB: ProgramNavRoutes("StrongLiftsWorkoutB")
    object PushWorkout: ProgramNavRoutes("PushWorkout")
    object PullWorkout: ProgramNavRoutes("PullWorkout")
    object LegWorkout: ProgramNavRoutes("LegWorkout")
}
