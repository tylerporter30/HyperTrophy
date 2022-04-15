package com.example.hypertrophy.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.hypertrophy.database.Program
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class ProgramConverter {
    @TypeConverter
    fun fromString(value: String): List<Template> {
        val type = object :
            TypeToken <List<Template>>() {}.type
        return Gson()
            .fromJson<List<Template>>(value, type)
    }

    @TypeConverter
    fun listToString(templates: List<Template>): String {
        val gson = Gson()
        return gson.toJson(templates)
    }
}