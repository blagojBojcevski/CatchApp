package com.test.testapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*
import javax.annotation.concurrent.Immutable

@Entity
@Immutable
data class User(
    @PrimaryKey val id: String,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("email")
    val email: String = "",

    @SerializedName("phone")
    val phone: String? = "",

    @SerializedName("token")
    var token: String? = "",

    @SerializedName("address")
    val address: String? = "",

    var isLogged: Boolean = false
)