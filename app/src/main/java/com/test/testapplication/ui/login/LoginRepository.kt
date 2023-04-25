package com.test.testapplication.ui.login

import com.test.testapplication.data.service.ApiService
import com.test.testapplication.data.request.LoginRequest
import javax.inject.Inject

class LoginRepository  @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun login(loginRequest: LoginRequest) = apiService.login(loginRequest)

}