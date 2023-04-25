package com.test.testapplication.data.service

import com.test.testapplication.data.model.User
import com.test.testapplication.data.model.Vehicle
import com.test.testapplication.data.request.LoginRequest
import com.test.testapplication.data.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService{

    @GET("v1/users/{userId}/vehicles")
    suspend fun getVehicles(
        @Path(value = "userId", encoded = false) key: String,
        @Header("Authorization") token: String
    ): Response <List<Vehicle>>

    @POST("v1/users/{userId}/vehicles")
    suspend fun createVehicles(
        @Path(value = "userId", encoded = false) key: Long,
        @Query("api_key") api_key: String,
    ): Response<User>

    @POST("v1/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
}