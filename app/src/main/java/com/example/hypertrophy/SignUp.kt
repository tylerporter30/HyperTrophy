package com.example.hypertrophy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hypertrophy.viewModel.User
import com.example.hypertrophy.viewModel.UserViewModel

@Composable
fun SignUpScreen(
    navController: NavHostController,
    userViewModel: UserViewModel
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(R.string.sign_up)) }) },
        content = { SignUpContent(
            navController = navController,
            userViewModel = userViewModel
        ) }
    )
}

@Composable
fun SignUpContent(
    navController: NavHostController,
    userViewModel: UserViewModel
) {
    val searchResults by userViewModel.searchResults.observeAsState(listOf())

    val allUsers by userViewModel.allUsers.observeAsState(listOf())

    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.htbackground),
            contentDescription = "background",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.matchParentSize(),
            alpha = 0.3f
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            //Text(text = stringResource(R.string.sign_up))

            val focusManager = LocalFocusManager.current

            var UsernameInput by remember { mutableStateOf("") }
            var PasswordInput by rememberSaveable { mutableStateOf("") }

            val onUsernameChange = { text: String ->
                UsernameInput = text
            }

            var showPassword by rememberSaveable { mutableStateOf(false) }

            OutlinedTextField(
                value = UsernameInput,
                onValueChange = onUsernameChange,
                label = {
                    Text(text = stringResource(R.string.username))
                },
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions.Default
            )

            OutlinedTextField(
                value = PasswordInput,
                onValueChange = { text: String ->
                    PasswordInput = text
                },
                label = {
                    Text(text = stringResource(R.string.password))
                },
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                visualTransformation = if (showPassword) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    if (showPassword) {
                        IconButton(onClick = { showPassword = false }) {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                contentDescription = ""
                            )
                        }
                    } else {
                        IconButton(onClick = { showPassword = true }) {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                contentDescription = ""
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions.Default
            )

            Button(
                onClick = {
                    userViewModel.addUser(user = User(username = UsernameInput, password = PasswordInput))
                    navController.navigate(NavRoutes.Login.route)
                    focusManager.clearFocus()
                          },
                enabled = PasswordInput.isNotBlank() && UsernameInput.isNotBlank(),

                contentPadding = PaddingValues(start = 120.dp,end = 120.dp, top = 15.dp, bottom = 15.dp)
            ) {
                Text(text = stringResource(R.string.sign_up))
            }

            //Text(text = allUsers.size.toString())

        }
    }
}

