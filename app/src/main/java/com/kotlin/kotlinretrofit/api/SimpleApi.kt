package com.kotlin.kotlinretrofit.api

import com.kotlin.kotlinretrofit.model.Post
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleApi
{
    @GET("posts/1")
    suspend fun getPost():Response<Post>

    // (/)
    @GET("posts/{postNumber}")
    suspend fun getPost2(@Path("postNumber") number:Int):Response<Post>

    // (?)
    @GET("posts")
    suspend fun getCustomPost(@Query("userId") userId:Int): Response<List<Post>>

    // (? - & - &)
    @GET("posts")
    suspend fun getCustomPos2(
        @Query("userId") userId:Int,
        @Query("_sort") sort:String,
        @Query("_order") order:String
    ): Response<List<Post>>

}