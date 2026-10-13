package com.mdi2.todo

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TodoScreenTest {
    @get:Rule
    // this rule allows us to run Compose UI Tets
    val composeTestRule = createComposeRule()

    // A fresh TodoViewModel is created before each test
    private lateinit var _viewModel: TodoViewModel

    @Before
    fun setup(){
        _viewModel = TodoViewModel()
        // set the content to display in the test - same as mAINaCTIVITY
        composeTestRule.setContent{
            TodoScreen(viewModel = _viewModel)
        }
    }

    @Test
    fun addTask_userTypesAndClicksAdd_taskAppearsInList(){
        // Arrange - the setup() already does this. Creates an empty viewModel launch the TodoScreen
        // Verify the task does not  exist yet
        composeTestRule.onNodeWithText("Buy Groceries").assertDoesNotExist()
        // Act - type a task and click add
        composeTestRule
            .onNodeWithTag("inputField")
            .performTextInput("Buy Groceries")

        // Click the button
        composeTestRule
            .onNodeWithTag("addButton")
            .performClick()

        composeTestRule
            .onNodeWithText("Buy Groceries")
            .assertIsDisplayed()
    }


    @Test
    fun addTask_withBlankInput_taskDoesNotAppearInList() {
        composeTestRule
            .onNodeWithTag("inputField")
            .performTextInput("     ")

        composeTestRule
            .onNodeWithTag("addButton")
            .performClick()

        composeTestRule
            .onNodeWithText("Tasks: 0")
            .assertIsDisplayed()
    }
}