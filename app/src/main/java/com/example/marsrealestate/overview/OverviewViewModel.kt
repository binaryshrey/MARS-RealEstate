package com.example.marsrealestate.overview

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsrealestate.network.MarsApi
import com.example.marsrealestate.network.MarsApiFilter
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

    private val _selectedProperty = MutableLiveData<MarsProperty>()
    val selectedProperty : LiveData<MarsProperty>
        get() = _selectedProperty


    init {
        getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL)
        Log.i("OverviewViewModel","init")
    }

    private fun getMarsRealEstateProperties(filter: MarsApiFilter) {
        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING

            try{
                _property.value = MarsApi.retrofitService.getProperties(filter.value)
                _status.value = MarsApiStatus.DONE
                Log.i("OverviewViewModel",_property.value.toString())
            } catch (e : Exception){
                Log.i("OverviewViewModel",e.toString())
                _status.value = MarsApiStatus.ERROR
                _property.value = ArrayList()

            }
        }
    }

    fun UpdateFilter(filter: MarsApiFilter){
        getMarsRealEstateProperties(filter)
    }

    fun onSelect(marsProperty: MarsProperty){
        _selectedProperty.value = marsProperty
    }
    fun onNavigateComplete(){
        _selectedProperty.value = null
    }
}