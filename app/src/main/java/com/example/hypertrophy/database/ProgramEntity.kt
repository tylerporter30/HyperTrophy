package com.example.hypertrophy.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "program")
data class Program(

    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,

    @ColumnInfo(name = "name")
    val name:String,

    @ColumnInfo(name = "template")
    val templates: List<Template>
)
