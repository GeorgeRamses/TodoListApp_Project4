package com.udacity.project4.locationreminders.savereminder

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.udacity.project4.FakeDataSource
import com.udacity.project4.MyApp
import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.reminderslist.ReminderDataItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SaveReminderViewModelTest {


    //TODO: provide testing to the SaveReminderView and its live data objects
    private lateinit var dataSource: ReminderDataSource
    private lateinit var viewModel: SaveReminderViewModel


    private val reminder1 = ReminderDTO("reminder1", "Desc1", "location1", 1.0, 1.0)
    private val reminder2 = ReminderDTO("reminder2", "Desc2", "location2", 2.0, 2.0)
    private val reminder3 = ReminderDTO("reminder3", "Desc3", "location3", 3.0, 3.0)
    private val newreminders = mutableListOf(reminder3)
    private val dataReminders = mutableListOf(reminder1, reminder2)

    @Before
    fun init() {
        dataSource = FakeDataSource(dataReminders)
        viewModel = SaveReminderViewModel(MyApp(), dataSource)
    }

    @Test
    fun testViewModel() = runBlocking {

        val remindersList = newreminders.map {
            ReminderDataItem(
                title = it.title,
                description = it.description,
                location = it.location,
                latitude = it.latitude,
                longitude = it.longitude,
                id = it.id
            )
        }
        viewModel.validateAndSaveReminder(remindersList[0])

        assertEquals(dataReminders[2], reminder3)
    }

}