package com.udacity.project4.locationreminders.data

import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.data.dto.Result
import com.udacity.project4.locationreminders.data.local.RemindersDao

//Use FakeDataSource that acts as a test double to the LocalDataSource
class FakeDataSource(var reminders: MutableList<ReminderDTO>? = mutableListOf()) : RemindersDao {

    override suspend fun getReminders(): List<ReminderDTO> {
        reminders?.let {
            return it
        }
        throw Exception("No Reminder")
    }

    override suspend fun saveReminder(reminder: ReminderDTO) {
        reminders?.add(reminder)
    }

    override suspend fun getReminderById(reminderId: String): ReminderDTO? {
        reminders?.let { r ->
            r.forEach {
                if (it.id == reminderId) {
                    return it
                }
            }
        }
        throw Exception("not found")
    }

    override suspend fun deleteAllReminders() {
        reminders?.clear()
    }


}