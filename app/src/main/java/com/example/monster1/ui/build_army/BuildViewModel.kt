package com.example.monster1.ui.build_army

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BuildViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Build Army Fragment"
    }
    val text: LiveData<String> = _text
}