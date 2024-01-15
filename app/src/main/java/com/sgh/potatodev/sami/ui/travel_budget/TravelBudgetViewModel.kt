package com.sgh.potatodev.sami.ui.travel_budget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TravelBudgetViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is travel budget Fragment"
    }
    val text: LiveData<String> = _text
}