package com.test.testapplication.ui.main

import com.test.testapplication.data.service.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService)
{

    suspend fun getVehicle(userId: String, token: String) = apiService.getVehicles(userId, token)
}