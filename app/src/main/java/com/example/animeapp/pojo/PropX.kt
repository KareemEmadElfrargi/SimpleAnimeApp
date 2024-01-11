package com.example.animeapp.pojo


import com.google.gson.annotations.SerializedName

data class PropX(
    @SerializedName("from")
    val from: FromX?,
    @SerializedName("to")
    val to: ToX?
)