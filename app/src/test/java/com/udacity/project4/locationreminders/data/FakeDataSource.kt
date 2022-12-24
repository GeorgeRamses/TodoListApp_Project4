package com.udacity.project4

import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.data.dto.Result

//Use FakeDataSource that acts as a test double to the LocalDataSource
class FakeDataSource(var reminders: MutableList<ReminderDTO>? = mutableListOf()) : ReminderDataSource {

    override suspend fun getReminders(): Result<List<ReminderDTO>> {
        reminders?.let {
            return Result.Success(ArrayList(it))
        }
        return Result.Error(Exception("No Reminder").message)
    }

//    override suspend fun getReminderById(reminderId: String): Result<ReminderDTO> {
//
//        reminders?.forEach {
//            if (it.id == reminderId) {
//                return Result.Success(it)
//            }
//        }
//        return Result.Error(Exception("Reminder not found").message)
//    }

    override suspend fun saveReminder(reminder: ReminderDTO) {
        reminders?.add(reminder)
    }

    override suspend fun getReminder(id: String): Result<ReminderDTO> {
        reminders?.forEach {
            if (it.id == id) {
                return Result.Success(it)
            }
        }
        return Result.Error(Exception("No Reminder").message)
    }


    override suspend fun deleteAllReminders() {
        reminders?.clear()
    }


}