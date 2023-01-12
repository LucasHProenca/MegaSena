package com.example.megasena

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ClassEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val numerosSorteados: String,
    val date: String,
)
