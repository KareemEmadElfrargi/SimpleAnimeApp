package com.example.animeapp.pojo


import com.example.animeapp.pojo.ImagesXXX
import com.google.gson.annotations.SerializedName

data class TrailerX(
    @SerializedName("embed_url")
    val embedUrl: String?,
    @SerializedName("images")
    val images: ImagesXXX?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("youtube_id")
    val youtubeId: String?
)