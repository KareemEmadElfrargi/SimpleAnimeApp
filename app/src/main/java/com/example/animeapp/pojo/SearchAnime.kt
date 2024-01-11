package com.example.animeapp.pojo


import com.google.gson.annotations.SerializedName

data class SearchAnime(
    @SerializedName("data")
    val `data`: List<DataX>?,
    @SerializedName("pagination")
    val pagination: PaginationX?
)