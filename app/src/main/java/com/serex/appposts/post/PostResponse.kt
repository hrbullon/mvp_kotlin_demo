package com.serex.appposts.post

import com.google.gson.annotations.SerializedName
import com.serex.appposts.post.model.Post

data class PostResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("data") val data: List<Post>
)

data class PostModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)


/*data class PostResponse(
    val code: Int,
    val data: List<Post>,
    val meta: Meta
)

data class Meta(
    val pagination: Pagination
)

data class Pagination(
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)*/