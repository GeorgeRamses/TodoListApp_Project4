package com.udacity.project4.locationreminders.reminderslist

import android.app.Application
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.udacity.project4.FakeDataSource
import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class RemindersListViewModelTest {

//    @get:Rule
//    var instantExecutorRule = InstantTaskExecutorRule()
//
//    @ExperimentalCoroutinesApi
//    @get:Rule
//    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var dataSource: ReminderDataSource
    private lateinit var viewModel: RemindersListViewModel


    private val reminder1 = ReminderDTO("reminder1", "Desc1", "location1", 1.0, 1.0)
    private val reminder2 = ReminderDTO("reminder2", "Desc2", "location2", 2.0, 2.0)
    private val reminder3 = ReminderDTO("reminder3", "Desc3", "location3", 3.0, 3.0)
    private val reminders = mutableListOf<ReminderDTO>()
    private val emptyReminders = mutableListOf<ReminderDTO>()

    @Before
    fun init() {
        dataSource = FakeDataSource(reminders)
        viewModel = RemindersListViewModel(Application(), dataSource)
    }

    @Test
    fun testReminderListEmpty() {
        viewModel.loadReminders()
        assertEquals(viewModel.showNoData, true)
    }

    @Test
    fun testViewModel() {
        reminders.add(reminder1)
        viewModel.loadReminders()
        val reminderList = viewModel.remindersList.value
        val dataList = reminders.map {
            ReminderDataItem(
                title = it.title,
                description = it.description,
                location = it.location,
                latitude = it.latitude,
                longitude = it.longitude,
                id = it.id
            )
        }

        assertEquals(reminderList, dataList)
    }


}