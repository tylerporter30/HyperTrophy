package com.example.hypertrophy.di

import android.content.Context
import androidx.room.Room
import com.example.hypertrophy.HyperTrophyApplication
import com.example.hypertrophy.database.ExerciseDao
import com.example.hypertrophy.database.ProgramConverter
import com.example.hypertrophy.database.ProgramDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideWeighInDao(database: ProgramDatabase): ExerciseDao {
        return database.exerciseDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): ProgramDatabase {
        return Room.databaseBuilder(
            appContext,
            ProgramDatabase::class.java,
            "program.db"
        ).addTypeConverter(ProgramConverter()).build()
    }
}