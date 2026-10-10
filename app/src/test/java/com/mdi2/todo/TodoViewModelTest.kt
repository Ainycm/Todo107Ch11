package com.mdi2.todo

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class TodoViewModelTest {

    // the system under test - recreated fresh before each test
    private lateinit var _viewModel: TodoViewModel

    // set up the viewmodel before each test
    @Before
    fun setup() {
        _viewModel = TodoViewModel()
    }

    @Test
    fun addTask_withValidTitle_appearsInList() {
        // Arrange - the setup() already does this. Creates an empty ViewModel
        // Act - call the function being tested
        _viewModel.addTask("Buy Groceries")

        // Assert - check the result
        assertEquals(1, _viewModel.getTaskCount())
        assertTrue(_viewModel.containsTask("Buy Groceries"))
    }

    // test a blank title should not be added
    @Test
    fun addTask_withBlankTitle_notAddTask() {
        // try to add a blank task
        _viewModel.addTask("")
        assertEquals(0, _viewModel.getTaskCount())
    }

    @Test
    fun addTask_withEmptyString_dosNotAdd(){
        _viewModel.addTask("")
        assertEquals(0, _viewModel.getTaskCount())
    }

    // Challenge: Leading and trailing spaces shouold be trimmed
    @Test
    fun addTask_withLeadingAndTrailingSpaces_trimsTitle() {
        // Act - add a task with spaces
        _viewModel.addTask("   Buy Groceries   ")

        // Assert - spaces should be removed
        assertEquals(1, _viewModel.getTaskCount())
        assertTrue(_viewModel.containsTask("Buy Groceries"))
    }

    @Test
    fun removeTask_withValidId_removesTask(){
        // Arrange - add task first so we have something to remove
        _viewModel.addTask("Buy Groceries")
        val taskId = _viewModel.task[0].id

        // act - call the function to being tested
        _viewModel.removeTask(taskId)

        // Assert - check the result
        assertEquals(0, _viewModel.getTaskCount())
        assertFalse(_viewModel.containsTask("Buy Groceries"))
    }

    @Test
    fun removeTask_removesCorrectTask_whenMultipleExist(){
        // when there are multiple tasks, remove the second one
        // Arrange - add multiple tasks
        _viewModel.addTask("A")
        _viewModel.addTask("B")
        _viewModel.addTask("C")

        // Act - cal the function being tested
        val idOfTaskToRemove = _viewModel.task[1].id
        _viewModel.removeTask(idOfTaskToRemove)

        // Assert - check the result
        assertEquals(2, _viewModel.getTaskCount())
        // Practice
        assertFalse(_viewModel.containsTask("B"))
        assertTrue(_viewModel.containsTask("A"))
        assertTrue(_viewModel.containsTask("C"))
    }
}