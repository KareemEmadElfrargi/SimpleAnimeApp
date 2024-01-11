package com.example.animeapp.pojo


import com.google.gson.annotations.SerializedName

data class TitleX(
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?
)