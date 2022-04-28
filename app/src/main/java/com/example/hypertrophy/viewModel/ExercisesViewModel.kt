package com.example.hypertrophy.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hypertrophy.data.ExerciseInfo
import com.example.hypertrophy.model.repository.ExerciseDBHelper
import kotlinx.coroutines.launch
import java.lang.Exception


class ExercisesViewModel:ViewModel() {

    var exerciseList: MutableState<List<ExerciseInfo>> = mutableStateOf(listOf())
    var bodyList: MutableState<List<String>> = mutableStateOf(listOf())
    var equipmentSelectedList: MutableState<List<String>> = mutableStateOf(listOf())

    var bodyPartList = listOf<String>("back",
        "cardio",
        "chest",
        "lower arms",
        "lower legs",
        "neck",
        "shoulders",
        "upper arms",
        "upper legs",
        "waist")

    val equipmentList = listOf(
        "assisted",
        "band",
        "barbell",
        "body Weight",
        "bOSU Ball",
        "cables",
        "dumbbell",
        "elliptical",
        "eZ Bar",
        "hammer",
        "kettlebell",
        "leverage Machine",
        "medicine Ball",
        "olympic Barbell",
        "resistance Band",
        "roller",
        "rope",
        "skierg Machine",
        "sled Machine",
        "smith Machine",
        "stability Ball",
        "stationary Bike",
        "stepmill Machine",
        "tire",
        "trap Bar",
        "upper Body Erometer",
        "weighted",
        "wheel Roller"
    )

    init{
        fetchBodyPart()
        fetchExercises()
        fetchEquipment()
    }

    private fun fetchExercises(){

        viewModelScope.launch {

            try {
                val fetchExercisesService = ExerciseDBHelper.getExerciseDBService()
                val responseService = fetchExercisesService.fetchAllExercises()

                if(responseService.isSuccessful){
                    responseService.body()?.let{
                            it ->
                        exerciseList.value = it
                    }
                }else{
                    responseService.errorBody()?.close()
                }
            }catch (e:Exception){

                Log.d("fetchExercises", "Exception in networking $e")
            }
        }
    }

    private fun fetchBodyPart(){

        viewModelScope.launch {

            try {
                val fetchExercisesService = ExerciseDBHelper.getExerciseDBService()
                val responseService = fetchExercisesService.fetchBodyPartList()

                if(responseService.isSuccessful){
                    responseService.body()?.let{
                            it ->
                        bodyList.value = it
                    }
                }else{
                    responseService.errorBody()?.close()
                }
            }catch (e:Exception){

                Log.d("fetchExercises", "Exception in networking $e")
            }
        }
    }
    private fun fetchEquipment(){

        viewModelScope.launch {

            try {
                val fetchExercisesService = ExerciseDBHelper.getExerciseDBService()
                val responseService = fetchExercisesService.fetchEquipmentList()

                if(responseService.isSuccessful){
                    responseService.body()?.let{
                            it ->
                        equipmentSelectedList.value = it
                    }
                }else{
                    responseService.errorBody()?.close()
                }
            }catch (e:Exception){

                Log.d("fetchExercises", "Exception in networking $e")
            }
        }
    }
}
