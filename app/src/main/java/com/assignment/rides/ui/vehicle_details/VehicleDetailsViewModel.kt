package com.assignment.rides.ui.vehicle_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VehicleDetailsViewModel : ViewModel() {

    private val _vin = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val vin: LiveData<String> = _vin

    private val _makemodel = MutableLiveData<String>().apply {
        value = ""
    }
    val makemodel: LiveData<String> = _makemodel

    private val _color = MutableLiveData<String>().apply {
        value = ""
    }
    val color: LiveData<String> = _color

    private val _cartype = MutableLiveData<String>().apply {
        value = ""
    }
    val text: LiveData<String> = _cartype
}