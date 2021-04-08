package com.serex.appposts.post.form

import com.serex.appposts.post.model.Post

class FormActivityPresenter(
    private val view: FormActivityView
) : FormActivityInteractor.OnFinishedListener {

    private val interactor = FormActivityInteractor(this)

    fun savePost(id: Int, title: String, body: String) {
        if (id > 0) {
            interactor.updatePost(id, title, body, 1)
        } else {
            interactor.createPost(title, body, 1)
        }
    }

    fun getPost(id: Int) {
        interactor.getPost(id)
    }

    override fun onPostLoaded(post: Post) {
        view.setDataForm(post)
    }

    override fun onSuccess(post: Post) {
        view.onAddSuccess(post)
    }

    override fun onError(message: String) {
        view.onAddError(message)
    }
}