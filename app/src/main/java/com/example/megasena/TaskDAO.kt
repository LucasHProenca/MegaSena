package com.example.megasena

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDAO {

    @Query("SELECT * FROM classentry")
    fun getAll(): List<ClassEntry>

    @Query("SELECT * FROM classentry WHERE id = :classId LIMIT 1 ")
    fun getTaskById(classId: Int): ClassEntry?

    @Insert
    fun insert(task: ClassEntry)

    @Query("DELETE FROM ClassEntry WHERE id = :classId")
    fun delete(classId: Int)

}