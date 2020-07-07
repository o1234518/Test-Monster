package com.example.monster1.ui.monster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MonsterViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Monster Fragment"
    }
    val text: LiveData<String> = _text
}