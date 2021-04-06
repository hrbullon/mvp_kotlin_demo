package com.serex.appposts.post.form

import com.serex.appposts.network.RetrofitInstance
import com.serex.appposts.post.PostService
import com.serex.appposts.post.detail.DetailActivityInteractor
import com.serex.appposts.post.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormActivityInteractor {

    private val service = RetrofitInstance.getRetroInstance().create(PostService::class.java)

    interface OnFinishedListener {
        fun onPostLoaded(post: Post)
        fun onSuccess(message: String)
        fun onError(message: String)
    }

    fun createPost(title: String, body: String, userId: Int, listener: OnFinishedListener){

        service.createPost(title,body,userId).enqueue(object: Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {

                if(response.code() == 201){
                    listener.onSuccess("Datos guardados satisfactoriamente!!")
                }else{
                    listener.onError("Error al guardar los datos")
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                listener.onError(t.message.toString())
            }
        })
    }

    fun updatePost(id:Int, title: String, body: String, userId: Int, listener: OnFinishedListener){

        service.updatePost(id,title,body,userId).enqueue(object: Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {

                if(response.code() == 200){
                    listener.onSuccess("Datos actualizados satisfactoriamente!!")
                }else{
                    listener.onError("Error al guardar los datos")
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                listener.onError(t.message.toString())
            }
        })
    }

    fun getPost(id: Int, listener: OnFinishedListener) {

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

}