package com.serex.appposts.post.detail

import com.serex.appposts.post.model.Post

class DetailActivityPresenter(
        var view: DetailActivityView,
        val interactor: DetailActivityInteractor
    ) : DetailActivityInteractor.OnLoadedFinishedListener {

    fun getPost(id:Int){
        interactor.getPost(id, this)
    }

    fun deletePost(id:Int){
        interactor.deletePost(id, this)
    }

    override fun onPostLoaded(post: Post) {
        view.showInfo(post)
    }

    override fun onSuccess(message: String) {
        view.onAddSuccess(message)
    }

    override fun onError(message: String) {
        view.onAddError(message)
    }
}