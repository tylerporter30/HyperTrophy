package com.example.hypertrophy.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hypertrophy.NavRoutes
import com.example.hypertrophy.R

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "HyperTrophy")} ) },
        content = { WelcomeContent(navController) },
        bottomBar = {  }
    )
}

@Composable
fun WelcomeContent(navController: NavHostController) {
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.htbackground),
            contentDescription = "background",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.matchParentSize(),
            alpha = 0.6f
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "HyperTrophy")
//            Icon(painter = painterResource(id = R.drawable.fitness), contentDescription = "")
            Image(
                painter = painterResource(id = R.drawable.htimage),
                contentDescription = "logo",
                modifier = Modifier.fillMaxWidth(0.6f),
//                contentScale = ContentScale.FillWidth,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { navController.navigate(NavRoutes.Login.route) }) {
                    Text(text = "Login")
                }

                Button(onClick = { navController.navigate(NavRoutes.SignUp.route) }) {
                    Text(text = "Sign Up")
                }

            }
        }
    }
}