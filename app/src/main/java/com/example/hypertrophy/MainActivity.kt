package com.example.hypertrophy

import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hypertrophy.ui.Screen_BrowseAllExercise
import com.example.hypertrophy.ui.Screen_History
import com.example.hypertrophy.ui.testHistory
import com.example.hypertrophy.programs.ProgramNavRoutes
import com.example.hypertrophy.programs.greyskull.GreySkullWorkoutALog
import com.example.hypertrophy.programs.greyskull.GreySkullWorkoutBLog
import com.example.hypertrophy.programs.pushpulllegs.LegWorkoutLog
import com.example.hypertrophy.programs.pushpulllegs.PullWorkoutLog
import com.example.hypertrophy.programs.pushpulllegs.PushWorkoutLog
import com.example.hypertrophy.programs.startingstrength.StartingStrengthWorkoutALog
import com.example.hypertrophy.programs.startingstrength.StartingStrengthWorkoutBLog
import com.example.hypertrophy.programs.stronglifts.StrongLiftsWorkoutALog
import com.example.hypertrophy.programs.stronglifts.StrongLiftsWorkoutBLog
import com.example.hypertrophy.ui.Screen_BrowseAllExercise
import com.example.hypertrophy.ui.theme.HyperTrophyTheme
import com.example.hypertrophy.ui.theme.WelcomeScreen
import com.example.hypertrophy.viewModel.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val programViewModel = ViewModelProvider(this).get(ProgramViewModel::class.java)
        val exercisesViewModel = ExercisesViewModel()
        val weighInViewModel: WeighInViewModel by viewModels()

        setContent {
            HyperTrophyTheme {
//                 A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {


                    SplashScreen(weighInViewModel, programViewModel = programViewModel)
                }
            }
        }
    }
}

@Composable
fun SplashScreen(
    weighInViewModel: WeighInViewModel,
    userViewModel: UserViewModel = UserViewModel(LocalContext.current.applicationContext as Application),
    historyViewModel: HistoryViewModel = HistoryViewModel(LocalContext.current.applicationContext as Application),
    programViewModel: ProgramViewModel

) {
    //This will hold the list of users registered on this device
    val searchResults by userViewModel.searchResults.observeAsState(listOf())

    //Create a navController here, send it through to the next screen as a parameter
    val navController = rememberNavController()

    //Implement a splashscreen here, then go to WelcomeScreen()
    MainScreen(
        navController = navController,
        weighInViewModel,
        userViewModel = userViewModel,
        historyViewModel = historyViewModel,
        programViewModel = programViewModel
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    navController: NavHostController,
    weighInViewModel: WeighInViewModel,
    userViewModel: UserViewModel,
    historyViewModel: HistoryViewModel,
    programViewModel: ProgramViewModel
) {
    //Create NavHost to navigate between welcome and login/signup
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Welcome.route
    ) {
        composable(NavRoutes.Welcome.route) {
            WelcomeScreen(
                navController = navController
            )
        }

        composable(NavRoutes.Login.route) {
            LoginScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }

        composable(NavRoutes.SignUp.route) {
            SignUpScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }

        composable(NavRoutes.Home.route) {
            HomeScreen(navController = navController, historyViewModel)
        }

        composable(NavRoutes.Analyze.route) {
            AnalyzeScreen(navController = navController)
        }

        composable(NavRoutes.Templates.route) {
            ListOfWorkoutTemplatesScreen(navController = navController)
        }

        composable(NavRoutes.Log.route) {
            LogScreen(navController = navController)
        }

        composable(NavRoutes.Programs.route) {
            SuggestedProgramsScreen(navController = navController, historyViewModel = historyViewModel)
        }

        composable(NavRoutes.Equipment.route) {
            EquipmentMatcherScreen(navController = navController)
        }

        composable(NavRoutes.WeighIn.route) {
            WeighInScreen(navController = navController, weighInViewModel)
        }

        composable(NavRoutes.CreateNewProgram.route) {
            CreateNewProgram(navController = navController, programViewModel = programViewModel)
        }

        composable(NavRoutes.Settings.route) {
            SettingsScreen(navController = navController)
        }

        composable(NavRoutes.CreateNewTemplate.route) {
            CreateNewTemplate(navController = navController,programViewModel)
        }

        composable(NavRoutes.Browse.route) {
            Screen_BrowseAllExercise(navController = navController, exercisesViewModel = ExercisesViewModel(),programViewModel)
        }

        composable(ProgramNavRoutes.StartingStrengthWorkoutA.route) {
            StartingStrengthWorkoutALog(navController = navController, historyViewModel)
        }

        composable(ProgramNavRoutes.StartingStrengthWorkoutB.route) {
            StartingStrengthWorkoutBLog(navController = navController, historyViewModel)
        }

        composable(ProgramNavRoutes.GreySkullWorkoutA.route) {
            GreySkullWorkoutALog(navController = navController, historyViewModel)
        }

        composable(ProgramNavRoutes.GreySkullWorkoutB.route) {
            GreySkullWorkoutBLog(navController = navController, historyViewModel)
        }

        composable(ProgramNavRoutes.StrongLiftsWorkoutA.route) {
            StrongLiftsWorkoutALog(navController = navController, historyViewModel)
        }

        composable(ProgramNavRoutes.StrongLiftsWorkoutB.route) {
            StrongLiftsWorkoutBLog(navController = navController, historyViewModel)
        }

        composable(ProgramNavRoutes.PushWorkout.route) {
            PushWorkoutLog(navController = navController, historyViewModel)
        }

        composable(ProgramNavRoutes.PullWorkout.route) {
            PullWorkoutLog(navController = navController, historyViewModel)
        }

        composable(ProgramNavRoutes.LegWorkout.route) {
            LegWorkoutLog(navController = navController, historyViewModel)
        }
        composable(NavRoutes.ExerciseByEquipment.route) {
            ExerciseByEquipment(navController = navController,exercisesViewModel = ExercisesViewModel())
        }

    }
}


@Composable
fun BottomBarNavigation(navController: NavHostController) {
    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach{ navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                          navController.navigate(navItem.route)
                },
                icon = { Icon(imageVector = navItem.image, contentDescription = navItem.title) },
                label = { Text(text = navItem.title) }
            )
        }
    }
}