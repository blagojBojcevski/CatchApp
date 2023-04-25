package com.test.testapplication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.testapplication.data.model.Vehicle
import com.test.testapplication.data.persistence.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val userDao: UserDao
): ViewModel() {
    private val vehicleLiveData = MutableLiveData<List<Vehicle>>()

    fun getVehicle() = vehicleLiveData

    fun getVehicles() {
        viewModelScope.launch {
            val user = userDao.getUser()
            val authToken = "Bearer ${user.token}"
            val countries = mainRepository.getVehicle(user.id, authToken)
            when (countries.isSuccessful) {
                true -> {
                    with(countries.body().orEmpty()) {
                        var vehicleList = listOf<Vehicle>()

                        forEach { (id,userId,vehicleId,user) ->
                            vehicleList = vehicleList + Vehicle(id, userId,vehicleId, user)
                        }
                        vehicleLiveData.postValue(vehicleList)
                    }
                }
                else -> {
                    vehicleLiveData.postValue(mutableListOf())
                }
            }
        }
    }
}