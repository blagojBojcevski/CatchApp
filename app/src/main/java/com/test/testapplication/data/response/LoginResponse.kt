package com.test.testapplication.data.response

import com.google.gson.annotations.SerializedName
import com.test.testapplication.data.model.User

data class LoginResponse(
    @SerializedName("token")
    val token: String,

    @SerializedName("user")
    var user: User
)
