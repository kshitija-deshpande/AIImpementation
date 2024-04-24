package com.kshitija.aiimplementation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.core.widget.NestedScrollView
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kshitija.aiimplementation.adapter.PostAdapter
import com.kshitija.aiimplementation.api.PostApiClient
import com.kshitija.aiimplementation.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    private var postDataArrayList = ObservableArrayList<Post>()
    private lateinit var postAdapter: PostAdapter
    private lateinit var postRecyclerView: RecyclerView
    private lateinit var nestedScrollView: NestedScrollView

    private var currentPage = 1
    private var isLoading = false
    private var areThereMorePosts = true
    private val PAGE_SIZE = 20

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postRecyclerView = findViewById(R.id.posts_recycler_view)
        nestedScrollView = findViewById(R.id.nested_scroll_view)
        postAdapter = PostAdapter(postDataArrayList, this)
        postRecyclerView.adapter = postAdapter
        postRecyclerView.layoutManager = LinearLayoutManager(this)

        getPostByPage(currentPage)
        setUpOnScrollListener()
    }


    private fun getPostByPage(pageNum: Int) {
        val call = PostApiClient.postApiService.getPostsByPage(pageNum)

        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val posts = response.body()
                    posts?.let {
                        if (posts.size == 0) {
                            areThereMorePosts = false
                        } else {
                            postDataArrayList.addAll(it)
                            postAdapter.notifyDataSetChanged()
                        }
                    }
                    print("This is the expected response for Posts: $posts For current page: $currentPage")
                } else {
                    print("There is an error occurred")
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                print("There is an error occurred:" + t.message)
            }
        })
    }

    private fun getPostByPageWithFixedSize(pageNum: Int) {
        val call = PostApiClient.postApiService.getPostsByPageAndSize(pageNum, PAGE_SIZE)

        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val posts = response.body()
                    posts?.let {
                        if (posts.size == 0) {
                            areThereMorePosts = false
                        } else {
                            postDataArrayList.addAll(it)
                            postAdapter.notifyDataSetChanged()
                        }
                    }
                    print("This is the expected response for Posts: $posts For current page: $currentPage")
                } else {
                    print("There is an error occurred")
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                print("There is an error occurred:" + t.message)
            }
        })
    }

    private fun setUpOnScrollListener() {
        // Listen for scroll event to fetch more data
        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

            if (v.getChildAt(v.childCount - 1) != null) {
                if (!isLoading && (scrollY >= (v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight)) && scrollY > oldScrollY) {
                    // Scrolled to bottom
                    if (areThereMorePosts) {
                        currentPage += 1
                        isLoading = true
                        Log.i(TAG, "fetchMoreOnScroll: End of the scroll detected, fetch next page posts.")
                        getPostByPage(currentPage)
                        isLoading = false
                    }
                }
            }
        })
    }

}