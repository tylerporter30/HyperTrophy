package com.example.hypertrophy.viewModel

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel

class WeighInViewModel : ViewModel() {
    var upperArmRight: String = ""
    var upperArmLeft: String = ""
}