package com.serex.appposts.post.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.serex.appposts.databinding.ActivityListBinding
import com.serex.appposts.post.form.FormActivity
import com.serex.appposts.post.detail.DetailActivity
import com.serex.appposts.post.list.adapters.PostAdapter
import com.serex.appposts.post.model.Post

class ListActivity : AppCompatActivity(), ListActivityView, PostAdapter.OnItemClickListener {

    private val posts = ArrayList<Post>()

    lateinit var postAdapter: PostAdapter
    private val presenter = ListActivityPresenter(this, ListActivityInteractor())
    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getPosts()

        binding.btnToCreate.setOnClickListener { navigateToCreate() }

    }

    override fun getPosts() {
        presenter.getPosts()
    }

    override fun onLoadedPosts(posts: List<Post>) {
        initRecyclerView(posts)
    }

    override fun onAddError(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }


    override fun navigateToCreate() {
        startActivity(Intent(this, FormActivity::class.java))
    }

    override fun initRecyclerView(posts: List<Post>) {

        postAdapter = PostAdapter(posts as ArrayList<Post>, this)
        val recyclerView = binding.rvPosts
        recyclerView.adapter = postAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        
    }

    override fun onViewItemClick(view: View?, position: Int) {
        var post = postAdapter.getItem(position)
        startActivity(Intent(this, DetailActivity::class.java).putExtra("post", post.id.toString()))
    }



}

