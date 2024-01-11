package com.example.animeapp.pojo


import com.example.animeapp.pojo.ImagesX
import com.google.gson.annotations.SerializedName

data class Trailer(
    @SerializedName("embed_url")
    val embedUrl: String?,
    @SerializedName("images")
    val images: ImagesX?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("youtube_id")
    val youtubeId: String?
)