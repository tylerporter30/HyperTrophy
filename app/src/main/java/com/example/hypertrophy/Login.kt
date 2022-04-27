package com.example.hypertrophy

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
fun LoginScreen(
    navController: NavHostController,
    userViewModel: UserViewModel
) {
    Scaffold(
        topBar = { TopAppBar( title = { Text(text = stringResource(R.string.login)) } ) },
        content = { LoginContent(
            navController = navController,
            userViewModel = userViewModel
        ) }
    )
}

@Composable
fun LoginContent(
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
            //Text(text = stringResource(R.string.login))

            val context = LocalContext.current
            val focusManager = LocalFocusManager.current

            var UsernameInput by rememberSaveable { mutableStateOf("") }
            var PasswordInput by rememberSaveable { mutableStateOf("") }

            var showPassword by rememberSaveable { mutableStateOf(false) }

            var loginCorrect by rememberSaveable { mutableStateOf(false)}

            OutlinedTextField(
                value = UsernameInput,
                onValueChange = { text: String ->
                    UsernameInput = text
                },
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

            allUsers.forEach() {
                if(it.username.equals(UsernameInput)) {
                    //Text(text = "Correct Username")
                    loginCorrect = it.password.equals(PasswordInput)
                } else {
                    loginCorrect = false
                }
                
            }

            Button(
                onClick = {
                    if(loginCorrect) {
                        navController.navigate(NavRoutes.Home.route)
                    } else {
                        Toast.makeText(context, "Incorrect Username or Password", Toast.LENGTH_LONG).show()
                    }
                    focusManager.clearFocus()
                          },
                enabled = PasswordInput.isNotBlank() && UsernameInput.isNotBlank() //This is only commented out for development purposes.
            ) {
                Text(text = stringResource(R.string.login))
            }

            /*Text(text = allUsers.size.toString())

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(allUsers) {
                    Row() {
                        Text(text = it.username)
                    }
                }
            }*/

        }
    }
}