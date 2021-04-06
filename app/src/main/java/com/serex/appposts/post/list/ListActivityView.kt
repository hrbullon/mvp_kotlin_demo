package com.serex.appposts.post.list

import com.serex.appposts.post.model.Post

interface ListActivityView {
    fun getPosts()
    fun onLoadedPosts(posts: List<Post>)
    fun onAddError(message: String)
    fun navigateToCreate()
    fun initRecyclerView(posts: List<Post>)
}