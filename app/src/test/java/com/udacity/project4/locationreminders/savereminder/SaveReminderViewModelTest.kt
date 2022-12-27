package com.udacity.project4.locationreminders.savereminder

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.udacity.project4.FakeDataSource
import com.udacity.project4.R
import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.reminderslist.ReminderDataItem
import com.udacity.project4.utils.SingleLiveEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
        viewModel = SaveReminderViewModel(ApplicationProvider.getApplicationContext(), dataSource)
    }

    @Test
    fun testValidate() {
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

        assertEquals(viewModel.validateEnteredData(remindersList[0]), true)
    }

    @Test
    fun shouldReturnErrorTitle() {
        val missingTitle = ReminderDataItem(null, null, "null", null, null)
        var validation = viewModel.validateEnteredData(missingTitle)
        val snackBarValue: SingleLiveEvent<Int> = viewModel.showSnackBarInt
        assertEquals(snackBarValue.value, (R.string.err_enter_title))
        assertEquals(validation, false)
    }

    @Test
    fun shouldReturnErrorLocation() {
        val missingLocation = ReminderDataItem("null", null, null, null, null)
        val validation = viewModel.validateEnteredData(missingLocation)
        assertEquals(viewModel.showSnackBarInt.value, (R.string.err_select_location))
        assertEquals(validation, false)
    }

    @Test
    fun testSave() {

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
        viewModel.saveReminder(remindersList[0])

        assertEquals(dataReminders[2], reminder3)
        assertEquals(viewModel.showToast.value, ("Reminder Saved !"))
    }


    @Test
    fun testDataCleared() {
        viewModel.onClear()

        assertEquals(viewModel.reminderTitle.value, null)
        assertEquals(viewModel.reminderDescription.value, null)
        assertEquals(viewModel.reminderSelectedLocationStr.value, null)
        assertEquals(viewModel.selectedPOI.value, null)
        assertEquals(viewModel.latitude.value, null)
        assertEquals(viewModel.longitude.value, null)
    }

}