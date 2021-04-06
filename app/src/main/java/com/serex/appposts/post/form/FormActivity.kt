package com.serex.appposts.post.form

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.serex.appposts.databinding.ActivityFormBinding
import com.serex.appposts.post.list.ListActivity
import com.serex.appposts.post.model.Post

class FormActivity : AppCompatActivity(), FormActivityView {

    private var post_id = 0
    private val presenter = FormActivityPresenter(this, FormActivityInteractor())
    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        intent?.extras?.let {
            post_id = it.getString("post")!!.toInt()
            presenter.getPost(post_id)
        }

        binding.btnSavePost.setOnClickListener { onClickBtnSave() }
        binding.btnToList.setOnClickListener { navigateToList() }

    }

    override fun onAddSuccess(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, ListActivity::class.java))
    }

    override fun onAddError(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun setDataForm(post: Post) {
        binding.title.setText(post.title)
        binding.body.setText(post.body)
    }


    override fun navigateToList() {
        startActivity(Intent(this, ListActivity::class.java))
    }

    override fun onClickBtnSave() {
        val title = binding.title.text.toString()
        val body = binding.body.text.toString()
        presenter.savePost(post_id,title, body)
    }
}