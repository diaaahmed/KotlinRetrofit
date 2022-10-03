package com.kotlin.kotlinretrofit.repository

import com.kotlin.kotlinretrofit.api.RetrofitInstance
import com.kotlin.kotlinretrofit.model.Post
import retrofit2.Response

typealias Sort = String
typealias Order = String

class Repository
{
    suspend fun getPost():Response<Post>
    {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPost2(number:Int):Response<Post>
    {
        return RetrofitInstance.api.getPost2(number)
    }

    suspend fun getCustomPost(number:Int):Response<List<Post>>
    {
        return RetrofitInstance.api.getCustomPost(number)
    }

    suspend fun getCustomPost2(userId:Int, sort:Sort, order:Order):Response<List<Post>>
    {
        return RetrofitInstance.api.getCustomPos2(userId,sort,order)
    }
}