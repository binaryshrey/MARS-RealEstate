package com.example.marsrealestate.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsrealestate.network.MarsApi
import com.example.marsrealestate.network.MarsProperty
import kotlinx.coroutines.launch


enum class MarsApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<MarsApiStatus>()
    val status : LiveData<MarsApiStatus>
        get() = _status

    private val _property = MutableLiveData<List<MarsProperty>>()
    val property : LiveData<List<MarsProperty>>
        get() = _property


    init {
        getMarsRealEstateProperties()
        Log.i("OverviewViewModel","init")
    }

    private fun getMarsRealEstateProperties() {
        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING

            try{
                _property.value = MarsApi.retrofitService.getProperties()
                _status.value = MarsApiStatus.DONE
                Log.i("OverviewViewModel",_property.value.toString())
            } catch (e : Exception){
                Log.i("OverviewViewModel",e.toString())
                _status.value = MarsApiStatus.ERROR
                _property.value = ArrayList()

            }
        }
    }
}