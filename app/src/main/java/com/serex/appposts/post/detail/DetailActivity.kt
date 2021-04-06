package com.serex.appposts.post.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.serex.appposts.databinding.ActivityDetailBinding
import com.serex.appposts.post.form.FormActivity
import com.serex.appposts.post.list.ListActivity
import com.serex.appposts.post.model.Post

class DetailActivity : AppCompatActivity(), DetailActivityView {

    private var post_id = 0
    private val presenter = DetailActivityPresenter(this, DetailActivityInteractor())
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        post_id = intent?.extras?.getString("post")!!.toInt()

        getPost(post_id)

        binding.btnDelPost.setOnClickListener { deletePost(post_id) }
        binding.btnEditPost.setOnClickListener { updatePost(post_id) }

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


