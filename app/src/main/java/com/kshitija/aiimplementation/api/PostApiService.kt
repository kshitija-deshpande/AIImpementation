package com.kshitija.aiimplementation.api

import com.kshitija.aiimplementation.models.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostApiService {
    @GET("posts/{id}")
    fun getPostById(@Path("id") postId: Int): Call<Post>

    @GET("posts/")
    fun getPosts(): Call<List<Post>>

    @GET("posts?")
    fun getPostsByPage(@Query("_page") pageNum: Int): Call<List<Post>>

    @GET("posts?")
    fun getPostsByPageAndSize(@Query("_page") pageNum: Int, @Query("_per_page") perPage: Int): Call<List<Post>>
}
