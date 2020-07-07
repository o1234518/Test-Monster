package com.example.monster1.ui.building

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BuildingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Buildings Fragment"
    }
    val text: LiveData<String> = _text
}