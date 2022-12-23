package com.udacity.project4.locationreminders.savereminder

import android.app.Application
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.udacity.project4.FakeDataSource
import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.reminderslist.ReminderDataItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
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
    private val reminders = mutableListOf(reminder1)

    @Before
    fun init() {
        dataSource = FakeDataSource(reminders)
        viewModel = SaveReminderViewModel(Application(), dataSource)
    }

    @Test
    fun testViewModel() {

        val remindersList = reminders.map {
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
        Assert.assertEquals(viewModel.reminderTitle, remindersList[0])
    }

}