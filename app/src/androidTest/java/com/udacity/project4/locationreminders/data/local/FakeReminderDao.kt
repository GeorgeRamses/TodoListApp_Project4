package com.udacity.project4.locationreminders.data.local

import com.udacity.project4.locationreminders.data.dto.ReminderDTO

class FakeReminderDao(val reminderList: MutableList<ReminderDTO>? = mutableListOf()) : RemindersDao {
    override suspend fun getReminders(): List<ReminderDTO> {
        reminderList?.let {
            return it.toList()
        }
        return emptyList()
    }

    override suspend fun getReminderById(reminderId: String): ReminderDTO? {
        reminderList?.let { it ->
            it.forEach {
                if (it.id == reminderId) {
                    return it
                }
            }
        }
        return null
    }

    override suspend fun saveReminder(reminder: ReminderDTO) {
        reminderList?.add(reminder)
    }

    override suspend fun deleteAllReminders() {
        reminderList?.clear()
    }
}