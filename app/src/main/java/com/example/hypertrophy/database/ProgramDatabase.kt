package com.example.hypertrophy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hypertrophy.data.PersonalWeightInRecord

@Database(entities = [Program::class,PersonalWeightInRecord::class], version = 2, exportSchema = false)
@TypeConverters(ProgramConverter::class)
abstract class ProgramDatabase: RoomDatabase() {

    abstract fun exerciseDao():ExerciseDao

    companion object{

        @Volatile
        private var INSTANCE:ProgramDatabase ?= null

        fun getDataBase(context: Context):ProgramDatabase{

            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){

                val instance = Room.databaseBuilder(context.applicationContext, ProgramDatabase::class.java,"myDatabase")
                    .addTypeConverter(ProgramConverter())
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                return instance
            }
        }
    }
}