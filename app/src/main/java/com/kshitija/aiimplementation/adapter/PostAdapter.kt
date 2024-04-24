package com.kshitija.aiimplementation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.kshitija.aiimplementation.R
import com.kshitija.aiimplementation.models.Post

class PostAdapter(
    recyclerDataArrayList: ObservableArrayList<Post>,
    context: Context
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    private val postDataArrayList: ObservableArrayList<Post>
    private val context: Context

    init {
        postDataArrayList = recyclerDataArrayList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.PostViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_view, parent, false)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostAdapter.PostViewHolder, position: Int) {
        // Fetch data
        val currentPost = postDataArrayList.get(position)

        // Assign data to view holder one by one
        // This is nothing but putting data at position - position in the holder view one by one
        holder.postId.setText(currentPost.id.toString())
        holder.userId.setText(currentPost.userId.toString())
        holder.title.text = currentPost.title
        holder.body.text = currentPost.body
        holder.hideShowIcon.setOnClickListener {
            hideOrShow(holder.titleLayout.isVisible, holder)
        }
    }

    private fun hideOrShow(isVisible: Boolean, holder: PostAdapter.PostViewHolder) {
        if (isVisible) {
            // Hide them
            holder.userIdLayout.visibility = View.GONE
            holder.titleLayout.visibility = View.GONE
            holder.bodyLayout.visibility = View.GONE
            holder.hideShowIcon.setImageDrawable(context.getDrawable(R.drawable.next_arrow))
        } else {
            // Show them
            holder.userIdLayout.visibility = View.VISIBLE
            holder.titleLayout.visibility = View.VISIBLE
            holder.bodyLayout.visibility = View.VISIBLE
            holder.hideShowIcon.setImageDrawable(context.getDrawable(R.drawable.back_arrow))
        }
    }

    override fun getItemCount(): Int {
        return postDataArrayList.size
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postId: TextView
        val userId: TextView
        val title: TextView
        val body: TextView
        val hideShowIcon: ImageView

        val userIdLayout: LinearLayout
        val titleLayout: LinearLayout
        val bodyLayout: LinearLayout
        init {
            postId = itemView.findViewById(R.id.postId)
            userId = itemView.findViewById(R.id.userId)
            title = itemView.findViewById(R.id.title)
            body = itemView.findViewById(R.id.body)
            hideShowIcon = itemView.findViewById(R.id.hideShowIcon)

            userIdLayout = itemView.findViewById(R.id.userIdLayout)
            titleLayout = itemView.findViewById(R.id.titleLayout)
            bodyLayout = itemView.findViewById(R.id.bodyLayout)
        }
    }
}