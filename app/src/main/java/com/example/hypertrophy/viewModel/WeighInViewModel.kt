package com.example.hypertrophy.viewModel

 import android.util.Log
 import androidx.lifecycle.LiveData
 import androidx.lifecycle.MutableLiveData
 import androidx.lifecycle.ViewModel
 import androidx.lifecycle.viewModelScope
 import com.example.hypertrophy.data.PersonalWeightInRecord
 import com.example.hypertrophy.repository.WeighInRepository
 import dagger.hilt.android.lifecycle.HiltViewModel
 import kotlinx.coroutines.launch
 import java.math.BigDecimal
 import java.util.*
 import javax.inject.Inject

@HiltViewModel
class WeighInViewModel @Inject constructor(private val repository: WeighInRepository)
    : ViewModel() {

    // Date Selection
    val dateSelectedLive: LiveData<String>
        get() = dateSelected

    private val dateSelected = MutableLiveData<String>()
    private val calendar = Calendar.getInstance()
    val dateToday = arrayOf(
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    // Weight
    val weightPickerIntLive: LiveData<Int>
        get() = weightPickerInt
    val weightPickerDecLive: LiveData<Int>
        get() = weightPickerDec

    private val weightPickerInt = MutableLiveData<Int>()
    private var weightInt: Int = 0
    private val weightPickerDec = MutableLiveData<Int>()
    private var weightDec: Int = 0

    // Diet
    val calorieCountTodayLive: LiveData<Float>
        get() = calorieCountToday
    val calorieCountNowLive: LiveData<String>
        get() = calorieCountNow

    private val calorieCountToday = MutableLiveData<Float>()
    private var calorieToday = 0.0f
    private val calorieCountNow = MutableLiveData<String>()

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
    private var previousShoulder = 0.0f
    private val measureUpperArmLeft = MutableLiveData<String>()
    private var previousUpperArmLeft = 0.0f
    private val measureUpperArmRight = MutableLiveData<String>()
    private var previousUpperArmRight = 0.0f
    private val measureForearmLeft = MutableLiveData<String>()
    private var previousForearmLeft = 0.0f
    private val measureForearmRight = MutableLiveData<String>()
    private var previousForearmRight = 0.0f
    private val measureChest = MutableLiveData<String>()
    private var previousChest = 0.0f
    private val measureWaist = MutableLiveData<String>()
    private var previousWaist = 0.0f
    private val measureThighLeft = MutableLiveData<String>()
    private var previousThighLeft = 0.0f
    private val measureThighRight = MutableLiveData<String>()
    private var previousThighRight = 0.0f
    private val measureCalfLeft = MutableLiveData<String>()
    private var previousCalfLeft = 0.0f
    private val measureCalfRight = MutableLiveData<String>()
    private var previousCalfRight = 0.0f

    // Body Fat Percentage
    val bodyFatPickerIntLive: LiveData<Int>
        get() = bodyFatPickerInt
    val bodyFatPickerDecLive: LiveData<Int>
        get() = bodyFatPickerDec

    private val bodyFatPickerInt = MutableLiveData<Int>()
    private var bodyFatInt = 0
    private val bodyFatPickerDec = MutableLiveData<Int>()
    private var bodyFatDec = 0

    init {
        updateDateSelected(dateToday[0], dateToday[1], dateToday[2])
    }

    private fun compareSeparate(int1: Int, dec1: Int, int2: Int, dec2: Int): Boolean {
        return (int1 == int2) && (dec1 == dec2)
    }

    private fun separateToFloat(integer: Int?, decimal: Int?): Float {
        return if (integer !== null && decimal !== null) "$integer.$decimal".toFloat() else 0.0f
    }

    private fun extractDecimalAsInt(float: Float): Int {
//        val bigDecimal = float.toBigDecimal()
        return try {
            val integer = float.toInt()
//            bigDecimal.minus(integer).multiply(BigDecimal("10")).intValueExact()
            ((float - integer.toFloat()) * 10).toInt()
        } catch (ex: Exception) {
            Log.d(
                "WEIGH IN",
                "Failure to extract decimal value: ${ ex.localizedMessage }"
            )
            0
        }
    }

    fun updateDateSelected(year: Int, month: Int, dayOfMonth:Int) {
        var isNewDay = false
        val date = "${month+1}/$dayOfMonth/$year"
        dateSelected.value = date

        viewModelScope.launch {
            var initRecordList: List<PersonalWeightInRecord> =
                repository.fetchPersonalWeightInRecordByDate(date)

            if (initRecordList.isEmpty()) {
                isNewDay = true
                initRecordList = repository.fetchPersonalWeightInRecordByLast()
                }

            if (initRecordList.isNotEmpty()) {
                // initialize displayed values
                weightInt = initRecordList[0].weight.toInt()
                weightDec = extractDecimalAsInt(initRecordList[0].weight)
                updateWeightPickerInt(weightInt)
                updateWeightPickerDec(weightDec)

                calorieToday = if (isNewDay) 0.0f else initRecordList[0].diet.toFloat()
                updateCalorieCount(calorieToday.toString())

                previousShoulder = initRecordList[0].shoulder
                updateShoulder(previousShoulder.toString())
                previousUpperArmLeft = initRecordList[0].upperArmLeft
                updateUpperArmLeft(previousUpperArmLeft.toString())
                previousUpperArmRight = initRecordList[0].upperArmRight
                updateUpperArmRight(previousUpperArmRight.toString())
                previousForearmLeft = initRecordList[0].forearmLeft
                updateForearmLeft(previousForearmLeft.toString())
                previousForearmRight = initRecordList[0].forearmRight
                updateForearmRight(previousForearmRight.toString())
                previousChest = initRecordList[0].chest
                updateChest(previousChest.toString())
                previousWaist = initRecordList[0].waist
                updateWaist(previousWaist.toString())
                previousThighLeft = initRecordList[0].thighLeft
                updateThighLeft(previousThighLeft.toString())
                previousThighRight = initRecordList[0].thighRight
                updateThighRight(previousThighRight.toString())
                previousCalfLeft = initRecordList[0].calfLeft
                updateCalfLeft(previousCalfLeft.toString())
                previousCalfRight = initRecordList[0].calfRight
                updateCalfRight(previousCalfRight.toString())

                bodyFatInt = initRecordList[0].bodyFat.toInt()
                bodyFatDec = extractDecimalAsInt(initRecordList[0].bodyFat)
                updateBodyFatPickerInt(bodyFatInt)
                updateBodyFatPickerDec(bodyFatDec)
            }
        }
    }

    fun saveRecord() {
        val newRecord: PersonalWeightInRecord
        try {
            newRecord = PersonalWeightInRecord(
                date = dateSelected.value ?: throw NoSuchElementException(),
                weight = separateToFloat(weightPickerInt.value, weightPickerDec.value),
                diet = calorieCountToday.value?.toInt() ?: 0,
                upperArmLeft = measureUpperArmLeft.value?.toFloat() ?: 0.0f,
                upperArmRight = measureUpperArmRight.value?.toFloat() ?: 0.0f,
                forearmLeft = measureForearmLeft.value?.toFloat() ?: 0.0f,
                forearmRight = measureUpperArmRight.value?.toFloat() ?: 0.0f,
                chest = measureChest.value?.toFloat() ?: 0.0f,
                thighLeft = measureThighLeft.value?.toFloat() ?: 0.0f,
                thighRight = measureThighRight.value?.toFloat() ?: 0.0f,
                calfLeft = measureCalfLeft.value?.toFloat() ?: 0.0f,
                calfRight = measureCalfRight.value?.toFloat() ?: 0.0f,
                waist = measureWaist.value?.toFloat() ?: 0.0f,
                shoulder = measureShoulder.value?.toFloat() ?: 0.0f,
                bodyFat = separateToFloat(bodyFatPickerInt.value, bodyFatPickerDec.value)
            )

            viewModelScope.launch {
                repository.insertPersonalWeightInRecord(newRecord)
            }
            // Update previous values to disable save buttons
            weightInt = weightPickerInt.value ?: 0
            weightDec = weightPickerDec.value ?: 0
            calorieToday = calorieCountToday.value ?: 0.0f
            previousShoulder = measureShoulder.value?.toFloat() ?: 0.0f
            previousUpperArmLeft = measureUpperArmLeft.value?.toFloat() ?: 0.0f
            previousUpperArmRight = measureUpperArmRight.value?.toFloat() ?: 0.0f
            previousForearmLeft = measureForearmLeft.value?.toFloat() ?: 0.0f
            previousForearmRight = measureForearmRight.value?.toFloat() ?: 0.0f
            previousChest = measureChest.value?.toFloat() ?: 0.0f
            previousWaist = measureWaist.value?.toFloat() ?: 0.0f
            previousThighLeft = measureThighLeft.value?.toFloat() ?: 0.0f
            previousThighRight = measureThighRight.value?.toFloat() ?: 0.0f
            previousCalfLeft = measureCalfLeft.value?.toFloat() ?: 0.0f
            previousCalfRight = measureCalfRight.value?.toFloat() ?: 0.0f
            bodyFatInt = bodyFatPickerInt.value ?: 0
            bodyFatDec = bodyFatPickerDec.value ?: 0
        } catch (ex: Exception) {
            Log.d(
                "WEIGH IN",
                "Failure to save record: ${ ex.localizedMessage }"
            )
        }
    }

    fun enableSaveWeightButton(): Boolean {
        return when (weightPickerInt.value) {
            null, 0 -> true
            else -> !compareSeparate(
                weightPickerInt.value ?: 0,
                weightPickerDec.value ?: 0,
                weightInt,
                weightDec
                )
        }
    }

    fun enableSaveDietButton(): Boolean {
        return when (calorieCountToday.value) {
            null, 0.0f -> true
            calorieToday -> false
            else -> true
        }
    }

    fun enableSaveMeasurementsButton(): Boolean {
        var result: Boolean
        try {
            val isMatching =
                measureShoulder.value?.toFloat() == previousShoulder &&
                measureUpperArmLeft.value?.toFloat() == previousUpperArmLeft &&
                measureUpperArmRight.value?.toFloat() == previousUpperArmRight &&
                measureForearmLeft.value?.toFloat() == previousForearmLeft &&
                measureForearmRight.value?.toFloat() == previousForearmRight &&
                measureChest.value?.toFloat() == previousChest &&
                measureWaist.value?.toFloat() == previousWaist &&
                measureThighLeft.value?.toFloat() == previousThighLeft &&
                measureThighRight.value?.toFloat() == previousThighRight &&
                measureCalfLeft.value?.toFloat() == previousCalfLeft &&
                measureCalfRight.value?.toFloat() == previousCalfRight

            result = !isMatching
        } catch (ex: NumberFormatException) {
            Log.d(
                "WEIGH IN",
                "Measurement entry not a number: ${ ex.localizedMessage }"
            )
            result = false
        }

        return result
    }

    fun enableSaveBodyFatButton(): Boolean {
        return when (bodyFatPickerInt.value) {
            null, 0 -> true
            else -> !compareSeparate(
                bodyFatPickerInt.value ?: 0,
                bodyFatPickerDec.value ?: 0,
                bodyFatInt,
                bodyFatDec
            )
        }
    }

    fun updateWeightPickerInt(newValue: Int) {
        weightPickerInt.value = newValue
    }

    fun updateWeightPickerDec(newValue: Int) {
        weightPickerDec.value = newValue
    }

    fun updateCalorieCount(newValue: String) {
        var calorieNew = 0.0f

        try {
            calorieNew = newValue.toFloat()
        } catch (ex: NumberFormatException) {
            Log.d(
                "WEIGH IN",
                "Diet entry not a number: ${ ex.localizedMessage }"
            )
        }

        if (calorieNew > 0.0) {
            calorieCountToday.value = calorieNew + (calorieCountToday.value ?: 0.0f)
        }
    }

    fun updateCalorieNow(newValue: String) {
        calorieCountNow.value = newValue
    }

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

    fun updateBodyFatPickerInt(newValue: Int) {
        bodyFatPickerInt.value = newValue
    }

    fun updateBodyFatPickerDec(newValue: Int) {
        bodyFatPickerDec.value = newValue
    }
}