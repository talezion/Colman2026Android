package com.idz.colman2026class2.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idz.colman2026class2.model.Student

@Database(entities = [Student::class], version = 3)
abstract class AppLocalDbRepository: RoomDatabase() {
    abstract val studentDao: StudentDao
}