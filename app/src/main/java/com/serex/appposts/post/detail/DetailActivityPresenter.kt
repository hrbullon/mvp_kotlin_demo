package com.serex.appposts.post.detail

import com.serex.appposts.post.form.FormActivityInteractor
import com.serex.appposts.post.model.Post

class DetailActivityPresenter constructor(
    private val view: DetailActivityView
) : FormActivityInteractor.OnFinishedListener {
    private val interactor = FormActivityInteractor(this)

    fun getPost(id: Int) {
        interactor.getPost(id)
    }

    fun deletePost(id: Int) {
        interactor.deletePost(id)
    }

    override fun onPostLoaded(post: Post) {
        view.showInfo(post)
    }

    override fun onSuccess(post: Post) {
        TODO("Not yet implemented")
    }

    /*override fun onSuccess(message: Post?) {
        view.onAddSuccess(message)
    }*/

    override fun onError(message: String) {
        view.onAddError(message)
    }
}