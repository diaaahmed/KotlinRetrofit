package com.kotlin.kotlinretrofit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.kotlinretrofit.model.Post
import com.kotlin.kotlinretrofit.repository.Order
import com.kotlin.kotlinretrofit.repository.Repository
import com.kotlin.kotlinretrofit.repository.Sort
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private var repository: Repository): ViewModel()
{
    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponse3: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val myResponse4: MutableLiveData<Response<List<Post>>> = MutableLiveData()


    private val responseFlow:MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val _responseFlow:MutableLiveData<Response<List<Post>>> get() = responseFlow

    val _myResponse: LiveData<Response<Post>> get() = myResponse
    val _myResponse2: LiveData<Response<Post>> get() = myResponse2
    val _myResponse3: LiveData<Response<List<Post>>> get() = myResponse3
    val _myResponse4: LiveData<Response<List<Post>>> get() = myResponse4

    fun getNewPostFlow(number: Int)
    {
        viewModelScope.launch {
            repository.getNewPostFlow(number)
                .catch { Log.d("TAG", "getNewPostFlow error: ${it.message}") }
                .collect{
                    responseFlow.value = it
                }
        }
    }
    fun getPost()
    {
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }

    fun getPost2(number:Int)
    {
        viewModelScope.launch {
            val response = repository.getPost2(number)

            myResponse2.value = response
        }
    }

    fun getCustomPost(number:Int)
    {
        viewModelScope.launch {
            val response = repository.getCustomPost(number)
            myResponse3.value = response
        }
    }


    fun getCustomPost2(userId:Int, sort: Sort,order: Order)
    {
        viewModelScope.launch {
            val response = repository.getCustomPost2(userId,sort,order)
            myResponse4.value = response
        }
    }
}

