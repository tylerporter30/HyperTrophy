package com.example.hypertrophy

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.res.painterResource

object NavBarItems {
    val BarItems = listOf(
        /*BarItem(
            title = "Home",
            image = Icons.Filled.Home,
            route = "home"
        ),*/ // Commented this out because we replaced it with an IconButton in the TopAppBar
        BarItem(
            title = "Analyze",
            image = Icons.Default.Menu,
            route = "analyze"
        ),
        BarItem(
            title = "Templates",
            image = Icons.Default.AddCircle,
            route = "templates"
        ),
        BarItem(
            title = "Log",
            image = Icons.Filled.PlayArrow,
            route = "log"
        ),
        /*BarItem(
            title = "Programs",
            image = Icons.Filled.Settings,
            route = "programs"
        ),
        BarItem(
            title = "Equipment",
            image = Icons.Filled.Build,
            route = "equipment"
        ),*/  // Commented both of these out because the got moved to a dropdown menu in Templates
        BarItem(
            title = "WeighIn",
            image = Icons.Filled.AccountCircle,
            route = "weighIn"
        )
    )
}