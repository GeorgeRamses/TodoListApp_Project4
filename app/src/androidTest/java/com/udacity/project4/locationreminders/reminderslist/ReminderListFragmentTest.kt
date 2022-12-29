package com.udacity.project4.locationreminders.reminderslist

import android.app.Application
import android.app.DirectAction
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.udacity.project4.FakeDataSource
import com.udacity.project4.R
import com.udacity.project4.base.NavigationCommand
import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.savereminder.SaveReminderFragment
import com.udacity.project4.locationreminders.savereminder.SaveReminderFragmentDirections
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
//UI Testing
@MediumTest
class ReminderListFragmentTest {
    private lateinit var repository: ReminderDataSource
    private var shouldReturnError = false
    private var reminders = mutableListOf<ReminderDTO>()
    private lateinit var viewModel: RemindersListViewModel
    private val reminder1 = ReminderDTO("reminder1", "Desc1", "location1", 1.0, 1.0)

    @Before
    fun init() {
        reminders.add(reminder1)
        repository = FakeDataSource(reminders)
        viewModel = RemindersListViewModel(Application(), repository)
    }

    //    test the navigation of the fragments.
    @Test
    fun navigate_to_DetailsFragment() = runBlocking {
        repository.saveReminder(
            ReminderDTO("reminder2", "Desc2", "location2", 1.0, 1.0)
        )
        val scenario = launchFragment<ReminderListFragment>()

        val navController = mock(NavController::class.java)
        scenario.onFragment { Navigation.setViewNavController(it.view!!, navController) }
        onView(withId(R.id.title)).perform(
            RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                hasDescendant(
                    withText("reminder2")
                ), click()
            )
        )
        verify(navController).navigate(ReminderListFragmentDirections.toSaveReminder())
    }

    //    testing for the error messages.


    @Test
    fun testSnackBar() {
        viewModel.deleteAll()
        viewModel.loadReminders()
        onView(withText(viewModel.showSnackBar.value)).check(matches(isDisplayed()))
    }
}