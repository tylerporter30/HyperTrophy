package com.example.hypertrophy

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SuggestedProgramsScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar( title = {
            IconButton(onClick = { navController.navigate(NavRoutes.Templates.route) }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "Templates")
            }
            Text(text = "Programs") },

            actions = {
                var showMenu by rememberSaveable{ mutableStateOf(false) }
                IconButton(onClick = { showMenu = !showMenu }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Drop down menu")
                }

                DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {

                    DropdownMenuItem(onClick = {
                        //navController.navigate(NavRoutes.Equipment.route)
                    }) {
                        Text(text = "Starting Strength")
                    }

                    DropdownMenuItem(onClick = {
                        //navController.navigate(NavRoutes.Equipment.route)
                    }) {
                        Text(text = "GreySkull")
                    }

                    DropdownMenuItem(onClick = {
                        //navController.navigate(NavRoutes.Equipment.route)
                    }) {
                        Text(text = "StrongLifts 5x5")
                    }

                    DropdownMenuItem(onClick = {
                        //navController.navigate(NavRoutes.Equipment.route)
                    }) {
                        Text(text = "Push Day")
                    }

                    DropdownMenuItem(onClick = {
                        //navController.navigate(NavRoutes.Equipment.route)
                    }) {
                        Text(text = "Pull Day")
                    }

                    DropdownMenuItem(onClick = {
                        //navController.navigate(NavRoutes.Equipment.route)
                    }) {
                        Text(text = "Leg Day")
                    }
                }
            }






        ) },
        content = {  },
        //bottomBar = { BottomBarNavigation(navController = navController) }
    )
    FloatingActionButtonComponent()
}

@Composable
fun FloatingActionButtonComponent() {
    val context = LocalContext.current

Column(modifier = Modifier.fillMaxSize().padding(10.dp), verticalArrangement = Arrangement.Bottom,
    horizontalAlignment = Alignment.End) {


    FloatingActionButton(
        onClick = {
            Toast.makeText(context, "Thanks for clicking!", Toast.LENGTH_LONG).show()
        },
        modifier = Modifier.size(50.dp), shape = CircleShape, backgroundColor = Color.Black
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_plus),
            tint = Color.White, contentDescription = "Floating Button",
            modifier = Modifier.size(35.dp)
        )
    }
}
}


