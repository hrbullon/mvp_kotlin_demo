package com.serex.appposts.post.list.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.serex.appposts.R
import com.serex.appposts.databinding.PostItemBinding
import com.serex.appposts.post.model.Post


class PostAdapter(private var posts: ArrayList<Post> = ArrayList(), private var clickListener: OnItemClickListener):
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    interface OnItemClickListener {
        fun onViewItemClick(view: View?, position: Int)
    }

    fun addItem(post: Post) {
        posts.add(post)
        notifyItemInserted(itemCount)
    }

    fun setClickListener(itemClickListener: OnItemClickListener) {
        clickListener = itemClickListener
    }

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = PostItemBinding.bind(view)

        fun bind(post: Post) {
            binding.title.text = post.title.substring(0,10)
            binding.body.text  = post.body.substring(0,20)
        }

    }

    fun getItem(position: Int): Post {
        return posts[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int { return this.posts.size }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
        holder.itemView.setOnClickListener {
            clickListener.onViewItemClick(it, position)
        }
    }

}