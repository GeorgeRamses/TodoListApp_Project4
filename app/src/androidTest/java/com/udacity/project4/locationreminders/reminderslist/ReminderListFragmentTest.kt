package com.udacity.project4.locationreminders.reminderslist

import android.app.Application
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.udacity.project4.FakeDataSource
import com.udacity.project4.R
import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.savereminder.SaveReminderFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
//UI Testing
@MediumTest
class ReminderListFragmentTest {
    private lateinit var dataSource: ReminderDataSource
    private var shouldReturnError = false
    private var reminders = mutableListOf<ReminderDTO>()
    private lateinit var viewModel: RemindersListViewModel

    @Before
    fun init() {
        dataSource = FakeDataSource(reminders)
        viewModel = RemindersListViewModel(Application(), dataSource)
    }

    //    test the navigation of the fragments.
    @Test
    fun navigate_to_DetailsFragment() = runBlocking {
        launchFragmentInContainer<SaveReminderFragment>()
        onView(withId(R.id.saveReminder)).check(matches(isDisplayed()))
        onView(withId(R.id.saveReminder)).perform(click())
    }

    //    testing for the error messages.
    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    @Test
    fun ErrMessage() {

        viewModel.loadReminders()
        assertThat(viewModel.showNoData.value, `is`(true))
    }
}