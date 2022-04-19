package com.example.hypertrophy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personalWeightInRecord")
data class PersonalWeightInRecord(

    @PrimaryKey
    val date :String,

    @ColumnInfo(name = "weight")
    val weight:Float,

    @ColumnInfo(name = "diet")
    val diet:Int,

    @ColumnInfo(name = "upperArmLeft")
    val upperArmLeft:Float,
    @ColumnInfo(name = "upperArmRight")
    val upperArmRight:Float,
    @ColumnInfo(name = "forearmLeft")
    val forearmLeft:Float,
    @ColumnInfo(name = "forearmRight")
    val forearmRight:Float,
    @ColumnInfo(name = "chest")
    val chest:Float,
    @ColumnInfo(name = "thighLeft")
    val thighLeft:Float,
    @ColumnInfo(name = "thighRight")
    val thighRight:Float,
    @ColumnInfo(name = "calfLeft")
    val calfLeft:Float,
    @ColumnInfo(name = "calfRight")
    val calfRight:Float,
    @ColumnInfo(name = "waist")
    val waist:Float,
    @ColumnInfo(name = "shoulder")
    val shoulder:Float,

    @ColumnInfo(name = "bodyFat")
    val bodyFat:Float,
)
