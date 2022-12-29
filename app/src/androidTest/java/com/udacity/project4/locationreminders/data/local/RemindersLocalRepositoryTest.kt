package com.udacity.project4.locationreminders.data.local

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.data.dto.Result.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
//Medium Test to test the repository
@MediumTest
class RemindersLocalRepositoryTest {

    private val reminder1 = ReminderDTO("reminder1", "Desc1", "location1", 1.0, 1.0)
    private val reminder2 = ReminderDTO("reminder2", "Desc2", "location2", 2.0, 2.0)
    private val reminder3 = ReminderDTO("reminder3", "Desc3", "location3", 3.0, 3.0)
    private var remindersLocal = mutableListOf(reminder1, reminder2)

    private lateinit var reminderRepository: RemindersLocalRepository
    private lateinit var dataSource: FakeReminderDao


    @Before
    fun createRepository() {
        dataSource = FakeReminderDao(remindersLocal.toMutableList())

        reminderRepository = RemindersLocalRepository(dataSource, Dispatchers.Unconfined)
    }

    @Test
    fun save_and_get_Reminder() = runBlocking {
        var reminders = reminderRepository.getReminders() as Success
        assertEquals(reminders.data, remindersLocal)
        reminderRepository.saveReminder(reminder3)
        remindersLocal.add(reminder3)
        reminders = reminderRepository.getReminders() as Success
        assertEquals(reminders.data, remindersLocal)
    }

}