package com.serex.appposts.post.list

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.serex.appposts.databinding.ActivityListBinding
import com.serex.appposts.post.detail.DetailActivity
import com.serex.appposts.post.form.FormActivity
import com.serex.appposts.post.list.adapters.PostAdapter
import com.serex.appposts.post.model.Post
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListActivity : AppCompatActivity(), ListActivityView, PostAdapter.OnItemClickListener {

    private val posts = ArrayList<Post>()

    lateinit var postAdapter: PostAdapter
    private val presenter = ListActivityPresenter(this, ListActivityInteractor())
    private lateinit var binding: ActivityListBinding


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        intent?.extras?.let {
            val post = Post(12223, "titulo de prueba", "body vergatario", 1)
            posts.add(post)
            postAdapter.notifyDataSetChanged()
        }


        var observable = Observable.fromArray(posts)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        getPosts()

        observable.subscribe({
            println("pasando por aqui")
        }, {
            Log.e("ErrorTag", it.stackTraceToString())
        }, {
            println("onCompleted")
        })

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
        Intent(this, DetailActivity::class.java).apply {
            putExtra(POST, postAdapter.getItem(position).id)
            startActivity(this)
        }
    }

    companion object {
        const val POST = "post"
    }
}