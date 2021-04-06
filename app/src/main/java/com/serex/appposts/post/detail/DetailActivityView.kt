package com.serex.appposts.post.detail

import com.serex.appposts.post.model.Post

interface DetailActivityView {
    fun getPost(id:Int)
    fun showInfo(post:Post)
    fun onAddSuccess(message: String)
    fun onAddError(message: String)
    fun updatePost(id: Int)
    fun deletePost(id:Int)
}