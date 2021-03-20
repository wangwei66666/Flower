package com.ww.flower.ui.activities.ui.home

import androidx.lifecycle.*
import java.util.*

class HomeViewModel : ViewModel {

    private lateinit var handle: SavedStateHandle;

    companion object{
        val NAME = "NAME"
    }

    constructor(handle :SavedStateHandle){
        this.handle = handle
    }

    private val _text = MutableLiveData<String>().apply {

        value = "This is home Fragment xxx"
    }

    fun check(){
        if(handle!!.contains(NAME)){

        }
    }
    val text: LiveData<String> = _text
}