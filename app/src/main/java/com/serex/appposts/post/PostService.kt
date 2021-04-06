package com.serex.appposts.post

import com.serex.appposts.post.model.Post
import retrofit2.Call
import retrofit2.http.*

interface PostService {

    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Call<Post>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(@Field("title")  title: String,
                   @Field("body")   body: String,
                   @Field("userId") userId: Int): Call<Post>

    @FormUrlEncoded
    @PUT("posts/{id}")
    fun updatePost(@Path("id") id: Int,
                   @Field("title")  title: String,
                   @Field("body")   body: String,
                   @Field("userId") userId: Int): Call<Post>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id: Int): Call<Post>
}