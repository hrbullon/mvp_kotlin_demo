package com.serex.appposts.post.form

import com.serex.appposts.post.model.Post

class FormActivityPresenter(
        var view: FormActivityView,
        val interactor: FormActivityInteractor
        ) : FormActivityInteractor.OnFinishedListener {

    fun savePost(id: Int, title: String, body: String){
        if(id > 0){
            interactor.updatePost(id,title,body,1,this)
        }else{
            interactor.createPost(title,body,1,this)
        }
    }

    fun getPost(id:Int) {
        interactor.getPost(id, this)
    }

    override fun onPostLoaded(post: Post) {
        view.setDataForm(post)
    }

    override fun onSuccess(message: String) {
        view.onAddSuccess(message)
    }

    override fun onError(message: String) {
        view.onAddError(message)
    }
}