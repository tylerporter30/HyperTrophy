package com.example.hypertrophy.viewModel

 import android.app.DatePickerDialog
import android.os.Build
 import android.util.Log
 import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeighInViewModel : ViewModel() {
    // Weight
    val weightPickerIntLive: LiveData<Int>
        get() = weightPickerInt
    val weightPickerDecLive: LiveData<Int>
        get() = weightPickerDec

    private val weightPickerInt = MutableLiveData<Int>()
    private var weightInt = 189 // CHANGE TO FUNCTION CALL TO RETRIEVE LAST RECORD
    private val weightPickerDec = MutableLiveData<Int>()
    private var weightDec = 5   // CHANGE TO FUNCTION CALL TO RETRIEVE LAST RECORD

    fun updateWeightPickerInt(newValue: Int) {
        weightPickerInt.value = newValue
    }

    fun updateWeightPickerDec(newValue: Int) {
        weightPickerDec.value = newValue
    }

    fun saveWeightRecord() {
        /* TODO() */
    }

    /**
     * Replace with switch? (use cases to smart cast?)
     */
    fun enableSaveWeightButton() =
        !(weightPickerInt.value?.equals(weightInt) ?: false && weightPickerDec.value?.equals(weightDec) ?: false)

    // Diet
    val calorieCountTodayLive: LiveData<Double>
        get() = calorieCountToday
//    val calorieCountNowLive: LiveData<String>
//        get() = calorieCountNow

    private val calorieCountToday = MutableLiveData<Double>()
    private var calorieToday = 1543.0   // CHANGE TO FUNCTION CALL TO RETRIEVE CURRENT RECORD
//    private val calorieCountNow = MutableLiveData<String>()

    fun updateCalorieCount(newValue: String) {
        var calorieNew = 0.0

        try {
            calorieNew = newValue.toDouble()
        } catch (ex: NumberFormatException) {
            Log.d(
                "WEIGH IN",
                "Diet entry not a number: ${ ex.localizedMessage }"
            )
        }

        if (calorieNew > 0.0) {
            calorieCountToday.value = calorieNew + (calorieCountToday.value ?: 0.0)
        }
    }

    fun saveCalorieRecord() {
        /* TODO() */
    }

    fun enableSaveDietButton() = true   // TEMP

    // Measurements
    val measureShoulderLive: LiveData<String>
        get() = measureShoulder
    val measureUpperArmLeftLive: LiveData<String>
        get() = measureUpperArmLeft
    val measureUpperArmRightLive: LiveData<String>
        get() = measureUpperArmRight
    val measureForearmLeftLive: LiveData<String>
        get() = measureForearmLeft
    val measureForearmRightLive: LiveData<String>
        get() = measureForearmRight
    val measureChestLive: LiveData<String>
        get() = measureChest
    val measureWaistLive: LiveData<String>
        get() = measureWaist
    val measureThighLeftLive: LiveData<String>
        get() = measureThighLeft
    val measureThighRightLive: LiveData<String>
        get() = measureThighRight
    val measureCalfLive: LiveData<String>
        get() = measureCalfLeft
    val measureCalfRightLive: LiveData<String>
        get() = measureCalfRight

    private val measureShoulder = MutableLiveData<String>()
    private val measureUpperArmLeft = MutableLiveData<String>()
    private val measureUpperArmRight = MutableLiveData<String>()
    private val measureForearmLeft = MutableLiveData<String>()
    private val measureForearmRight = MutableLiveData<String>()
    private val measureChest = MutableLiveData<String>()
    private val measureWaist = MutableLiveData<String>()
    private val measureThighLeft = MutableLiveData<String>()
    private val measureThighRight = MutableLiveData<String>()
    private val measureCalfLeft = MutableLiveData<String>()
    private val measureCalfRight = MutableLiveData<String>()

    fun updateShoulder(newValue: String) {
        measureShoulder.value = newValue
    }

    fun updateUpperArmLeft(newValue: String) {
        measureUpperArmLeft.value = newValue
    }

    fun updateUpperArmRight(newValue: String) {
        measureUpperArmRight.value = newValue
    }

    fun updateForearmLeft(newValue: String) {
        measureForearmLeft.value = newValue
    }

    fun updateForearmRight(newValue: String) {
        measureForearmRight.value = newValue
    }

    fun updateChest(newValue: String) {
        measureChest.value = newValue
    }

    fun updateWaist(newValue: String) {
        measureWaist.value = newValue
    }

    fun updateThighLeft(newValue: String) {
        measureThighLeft.value = newValue
    }

    fun updateThighRight(newValue: String) {
        measureThighRight.value = newValue
    }

    fun updateCalfLeft(newValue: String) {
        measureCalfLeft.value = newValue
    }

    fun updateCalfRight(newValue: String) {
        measureCalfRight.value = newValue
    }

    fun saveMeasurementsRecord() {
        /* TODO() */
    }

    fun enableSaveMeasurementsButton() = true   // TEMP

    // Body Fat Percentage
    val bodyFatPickerIntLive: LiveData<Int>
        get() = bodyFatPickerInt
    val bodyFatPickerDecLive: LiveData<Int>
        get() = bodyFatPickerDec

    private val bodyFatPickerInt = MutableLiveData<Int>()
    private var bodyFatInt = 20 // CHANGE TO FUNCTION CALL TO RETRIEVE LAST RECORD
    private val bodyFatPickerDec = MutableLiveData<Int>()
    private var bodyFatDec = 5   // CHANGE TO FUNCTION CALL TO RETRIEVE LAST RECORD

    fun updateBodyFatPickerInt(newValue: Int) {
        bodyFatPickerInt.value = newValue
    }

    fun updateBodyFatPickerDec(newValue: Int) {
        bodyFatPickerDec.value = newValue
    }

    fun saveBodyFatRecord() {
        /* TODO() */
    }

    fun enableSaveBodyFatButton() =
        !(bodyFatPickerInt.value?.equals(bodyFatInt) ?: false && bodyFatPickerDec.value?.equals(bodyFatDec) ?: false)

}

fun StringToDouble(integer: MutableLiveData<Int>, decimal: MutableLiveData<Int>): Double {
    return "${ integer.value ?: 0 }.${ decimal.value ?: 0 }".toDouble()
}