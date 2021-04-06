package com.serex.appposts.post

import com.serex.appposts.post.model.Post

data class PostResponse(
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
)