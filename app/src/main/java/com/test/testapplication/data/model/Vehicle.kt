package com.test.testapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import javax.annotation.concurrent.Immutable

@Entity
@Immutable
data class Vehicle(

    @PrimaryKey val id: String,

    @SerializedName("userId")
    val userId: String = "",

    @SerializedName("vehicleId")
    val vehicleId: String = "",

    @SerializedName("user")
    val user: User? = null,

)
