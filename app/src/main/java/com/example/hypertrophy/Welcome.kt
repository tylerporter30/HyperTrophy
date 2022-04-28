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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hypertrophy.NavRoutes
import com.example.hypertrophy.R

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.app_name))} ) },
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
            Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.h2)
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
                Button(onClick = { navController.navigate(NavRoutes.Login.route) },
                    contentPadding = PaddingValues(start = 30.dp,end = 30.dp, top = 15.dp, bottom = 15.dp))
                {
                    Text(text = stringResource(id = R.string.login))
                }

                Button(onClick = { navController.navigate(NavRoutes.SignUp.route) },
                    contentPadding = PaddingValues(start = 24.dp,end = 24.dp, top = 15.dp, bottom = 15.dp))
                {
                    Text(text = stringResource(id = R.string.sign_up))
                }

            }
        }
    }
}