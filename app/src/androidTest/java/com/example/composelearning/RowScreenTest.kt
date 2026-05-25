package com.example.composelearning

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.composelearning.rowscreen.RowScreen
import com.example.composelearning.ui.theme.ComposeLearningTheme
import org.junit.Rule
import org.junit.Test

class RowScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rowScreen_displaysAllTexts() {
        // Start the app
        composeTestRule.setContent {
            ComposeLearningTheme {
                RowScreen()
            }
        }

        // Check if all texts are displayed
        composeTestRule.onNodeWithText("This is one").assertIsDisplayed()
        composeTestRule.onNodeWithText("This is two").assertIsDisplayed()
        composeTestRule.onNodeWithText("This is three").assertIsDisplayed()
    }
}
