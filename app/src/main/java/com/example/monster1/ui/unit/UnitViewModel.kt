package com.example.monster1.ui.unit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UnitViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Unit Fragment"
    }
    val text: LiveData<String> = _text
}