package com.serex.appposts.post.form

import com.serex.appposts.post.model.Post

interface FormActivityView {
    fun onAddSuccess(message: String)
    fun onAddError(message: String)
    fun setDataForm(post:Post)
    fun navigateToList()
    fun onClickBtnSave()
}