package com.serex.appposts.post.model

import com.google.gson.annotations.SerializedName

class Post(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
    @SerializedName("userId") val userId: Int
)
