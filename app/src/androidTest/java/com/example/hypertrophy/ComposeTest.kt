package com.example.hypertrophy

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.ViewModelProvider
import com.example.hypertrophy.ui.Screen_History
import com.example.hypertrophy.ui.Screen_Templates
import com.example.hypertrophy.ui.theme.HyperTrophyTheme
import com.example.hypertrophy.viewModel.ProgramViewModel
import org.junit.Rule
import org.junit.Test

class ComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test
    fun myTest() {
        // Start the app
        composeTestRule.setContent {
            HyperTrophyTheme {
                Screen_Templates()
            }
        }

        composeTestRule.onNodeWithContentDescription("TopAppBar").performClick()

        composeTestRule.onNodeWithText("Templates").assertIsDisplayed()
    }
}