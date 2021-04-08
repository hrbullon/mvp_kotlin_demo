package com.serex.appposts.post.list

import com.serex.appposts.network.RetrofitInstance
import com.serex.appposts.post.PostService
import com.serex.appposts.post.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivityInteractor {

    private val service = RetrofitInstance.getRetroInstance().create(PostService::class.java)

    interface OnLoadedFinishedListener {
        fun onSuccess(posts: List<Post>)
        fun onError(message: String)
    }

    fun getPosts(listener: OnLoadedFinishedListener){

        service.getPosts().enqueue(object :  Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {

                if(response.code() == 200){
                    response.body()?.let { listener.onSuccess(it) }
                }else{
                    listener.onError("Error al cargar la data")
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                listener.onError(t.message.toString())
            }
        })
    }
}
