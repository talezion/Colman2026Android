package com.idz.colman2026class2.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idz.colman2026class2.models.Student

@Database(entities = [Student::class], version = 2)
abstract class AppLocalDbRepository: RoomDatabase() {
    abstract val studentDao: StudentDao
}