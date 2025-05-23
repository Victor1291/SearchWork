package com.shu.searchwork.ui.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MessageViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Здесь будут сообщения!"
    }
    val text: LiveData<String> = _text
}