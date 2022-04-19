package com.example.hypertrophy.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hypertrophy.data.Template

@Entity(tableName = "program")
data class Program(

    @PrimaryKey
    val name:String,

    @ColumnInfo(name = "template")
    val templates: List<Template>
)