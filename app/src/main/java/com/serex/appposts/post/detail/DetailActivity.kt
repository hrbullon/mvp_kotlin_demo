package com.serex.appposts.post.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.serex.appposts.databinding.ActivityDetailBinding
import com.serex.appposts.post.form.FormActivity
import com.serex.appposts.post.list.ListActivity
import com.serex.appposts.post.list.ListActivity.Companion.POST
import com.serex.appposts.post.model.Post

class DetailActivity : AppCompatActivity(), DetailActivityView {

    private val presenter = DetailActivityPresenter(this)
    private val postId by lazy { intent?.extras?.getInt(POST) }
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        postId?.let {
            getPost(it)
            binding.btnDelPost.setOnClickListener { deletePost(postId!!) }
            binding.btnEditPost.setOnClickListener { updatePost(postId!!) }
        } ?: run {
            finish()
        }
    }

    override fun getPost(id: Int) {
        presenter.getPost(id)
    }

    override fun showInfo(post: Post) {
        binding.title.text = post.title
        binding.body.text = post.body
    }

    override fun onAddSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        startActivity(Intent(this, ListActivity::class.java))
    }

    override fun onAddError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun updatePost(id: Int) {
        startActivity(Intent(this, FormActivity::class.java).putExtra("post", id.toString()))
    }

    override fun deletePost(id: Int) {
        presenter.deletePost(id)
    }
}


