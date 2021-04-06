package com.serex.appposts.post.detail

import com.serex.appposts.network.RetrofitInstance
import com.serex.appposts.post.PostService
import com.serex.appposts.post.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivityInteractor {

    private val service = RetrofitInstance.getRetroInstance().create(PostService::class.java)

    interface OnLoadedFinishedListener{
        fun onPostLoaded(post: Post)
        fun onSuccess(message: String)
        fun onError(message: String)
    }

    fun getPost(id: Int, listener: DetailActivityInteractor.OnLoadedFinishedListener) {

        service.getPost(id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {

                if(response.code() == 200){
                    response.body()?.let { listener.onPostLoaded(it) }
                }else{
                    listener.onError("Error al cargar la data")
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun deletePost(id: Int, listener: DetailActivityInteractor.OnLoadedFinishedListener){

        service.deletePost(id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {

                if(response.code() == 200){
                    response.body()?.let { listener.onSuccess("Post eliminado") }
                }else{
                    listener.onError("Error al borrar post")
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}