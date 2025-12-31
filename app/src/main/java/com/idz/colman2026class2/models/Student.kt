package com.idz.colman2026class2.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(

    @PrimaryKey
    val id: String,

    val name: String,
    var isPresent: Boolean,
    val avatarUrlString: String?
)
