package com.udacity.project4.locationreminders.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.udacity.project4.FakeDataSource
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.data.local.RemindersLocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
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
    private var remindersLocal = listOf(reminder1, reminder2)

    private lateinit var reminderRepository: RemindersLocalRepository
    private lateinit var dataSource: FakeDataSource


    @Before
    fun createRepository() {
        dataSource = FakeDataSource(remindersLocal.toMutableList())

        reminderRepository = RemindersLocalRepository(dataSource, Dispatchers.Unconfined)
    }

    @Test
    fun getReminders() = runBlocking {

        val reminders = reminderRepository.getReminders()
        val remindersTest = Result.success(remindersLocal)
        assertThat(reminders, IsEqual(remindersTest))
    }

    @Test
    fun saveReminder() = runBlocking {

        reminderRepository.saveReminder(reminder3)
        val reminders = reminderRepository.getReminders()
        val remindersTest = Result.success(remindersLocal)
        assertThat(reminders, IsEqual(remindersTest))
    }

}