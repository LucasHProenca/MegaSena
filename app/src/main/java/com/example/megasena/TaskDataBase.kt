package com.example.megasena

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ClassEntry::class],
    version = 2
)
abstract class TaskDataBase: RoomDatabase(){

    abstract fun taskDao(): TaskDAO
}