package com.example.hypertrophy.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogViewModel : ViewModel()  {
    val exerciseNameLive: LiveData<String>
        get() = exerciseName
    val setsLive: LiveData<Int>
        get() = sets
    val repsLive: LiveData<Int>
        get() = reps
    val weightsIntLive: LiveData<Int>
        get() = weightsPickerInt
    val weightsDecLive: LiveData<Int>
        get() = weightsPickerDec

    private val exerciseName = MutableLiveData<String>()
    private val sets = MutableLiveData<Int>()
    private var setsStart = 1           // CHANGE TO FUNCTION CALL TO RETRIEVE LAST RECORD
    private val reps = MutableLiveData<Int>()
    private var repsStart = 5           // CHANGE TO FUNCTION CALL TO RETRIEVE LAST RECORD
    private val weightsPickerInt = MutableLiveData<Int>()
    private var weightsIntStart = 65    // CHANGE TO FUNCTION CALL TO RETRIEVE LAST RECORD
    private val weightsPickerDec = MutableLiveData<Int>()
    private var weightsDecStart = 0

    fun updateExerciseName() {
        /* TODO() */
    }

    fun updateSets() {
        sets.value = sets.value?.plus(1) ?: 1
        /**
         * TODO()
         * Need logic to handle:
         *   adding duplicate sets
         *   skipping sets
         *   completing sets
         *   triggering completing
         */
    }

    fun updateReps(newValue: Int) {
        reps.value = newValue
    }

    fun updateWeightsPickerInt(newValue: Int) {
        weightsPickerInt.value = newValue
    }

    fun updateWeightsPickerDec(newValue: Int) {
        weightsPickerDec.value = newValue
    }

    fun saveSetRecord() {
        /* TODO() */
    }
}