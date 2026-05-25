package com.example.composelearning

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.composelearning.radioButton.RadioButtonGroup
import com.example.composelearning.ui.theme.ComposeLearningTheme
import org.junit.Rule
import org.junit.Test

class RadioButtonNewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun radioButtonGroup_displaysOptionsAndHandlesSelection() {
        val options = listOf("Option 1", "Option 2", "Option 3")
        
        composeTestRule.setContent {
            var selectedOption by remember { mutableStateOf(options[0]) }
            ComposeLearningTheme {
                RadioButtonGroup(
                    options = options,
                    selectedOption = selectedOption,
                    onOptionSelected = { selectedOption = it }
                )
            }
        }

        // Verify all options are displayed
        options.forEach { option ->
            composeTestRule.onNodeWithText(option).assertIsDisplayed()
        }

        // Verify initial selection text
        composeTestRule.onNodeWithText("Selected: Option 1").assertIsDisplayed()

        // Perform click on Option 2
        composeTestRule.onNodeWithText("Option 2").performClick()

        // Verify selection text updated
        composeTestRule.onNodeWithText("Selected: Option 2").assertIsDisplayed()
    }

    @Test
    fun radioButtonNew_displaysLabelAndReflectsState() {
        composeTestRule.setContent {
            ComposeLearningTheme {
                RadioButtonGroup(
                    options = listOf("Test Label"),
                    selectedOption = "Test Label",
                    onOptionSelected = {}
                )
            }
        }

        // Verify label is displayed
        composeTestRule.onNodeWithText("Test Label").assertIsDisplayed()
        
        // Verify it is selected (since selectedOption matches label)
        composeTestRule.onNodeWithText("Test Label").assertIsSelected()
    }
}
