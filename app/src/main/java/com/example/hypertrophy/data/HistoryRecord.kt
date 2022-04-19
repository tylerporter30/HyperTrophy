package com.example.hypertrophy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historyRecord")
class HistoryRecord (

    @PrimaryKey
    val date:String,

    @ColumnInfo(name = "template")
    val templates: List<Template>
)
