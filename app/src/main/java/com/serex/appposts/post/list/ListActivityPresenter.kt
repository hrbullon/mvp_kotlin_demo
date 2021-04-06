package com.serex.appposts.post.list

import com.serex.appposts.post.model.Post

class ListActivityPresenter(
            var view: ListActivityView,
            val interactor: ListActivityInteractor
        ) : ListActivityInteractor.OnLoadedFinishedListener {

    fun getPosts() {
        interactor.getPosts(this)
    }

    override fun onSuccess(posts: List<Post>) {
        view.onLoadedPosts(posts)
    }

    override fun onError(message: String) {
        view.onAddError(message)
    }
}