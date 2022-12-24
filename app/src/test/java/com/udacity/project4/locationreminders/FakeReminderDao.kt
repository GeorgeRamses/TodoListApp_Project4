package com.udacity.project4.locationreminders

import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.data.local.RemindersDao

class FakeReminderDao(val reminderList: MutableList<ReminderDTO>? = mutableListOf()) : RemindersDao {
    override suspend fun getReminders(): List<ReminderDTO> {
        reminderList?.let {
            return it.toList()
        }
        throw Exception("No Data found")
    }

    override suspend fun getReminderById(reminderId: String): ReminderDTO? {
        reminderList?.let {
            it.forEach {
                if (it.id == reminderId) {
                    return it
                }
            }
        }
        throw Exception("Reminder Not Found")
    }

    override suspend fun saveReminder(reminder: ReminderDTO) {
        reminderList?.add(reminder)
    }

    override suspend fun deleteAllReminders() {
        reminderList?.clear()
    }
}