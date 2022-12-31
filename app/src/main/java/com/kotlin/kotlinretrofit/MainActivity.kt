package com.kotlin.kotlinretrofit

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlin.kotlinretrofit.databinding.ActivityMainBinding
import com.kotlin.kotlinretrofit.repository.Repository
import com.kotlin.kotlinretrofit.viewmodel.MainViewModel
import com.kotlin.kotlinretrofit.viewmodel.MainViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity()
{
    private lateinit var viewModel: MainViewModel
    private val ui by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ui.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        //viewModel.getPost2(7)

        //viewModel.getCustomPost(7)

        viewModel.getCustomPost2(7,"id","desc")

//        runBlocking {
//            viewModel.getNewPostFlow(6)
//
//            viewModel._responseFlow.observe(this@MainActivity)
//            {
//
//            }
//        }

        viewModel._myResponse4.observe(this, Observer{response->

            if (response.isSuccessful)
            {
                ui.txtTitle.text = response.body()?.get(1)?.title
                Log.d("here", "title is ${response.body()?.get(0)?.title}")
                Log.d("here", "body is ${response.body()?.get(0)?.body}")
            }
            else
            {
                ui.txtTitle.text = response.errorBody().toString()
            }
        })

//        viewModel._myResponse3.observe(this, Observer {response ->
//
//            if (response.isSuccessful)
//            {
//                ui.txtTitle.text = response.body()?.get(1)?.title
//                Log.d("here", "title is ${response.body()?.get(0)?.title}")
//                Log.d("here", "body is ${response.body()?.get(0)?.body}")
//            }
//            else
//            {
//                ui.txtTitle.text = response.errorBody().toString()
//            }
//        })

//        viewModel._myResponse2.observe(this, Observer {response ->
//            if (response.isSuccessful)
//            {
//                ui.txtTitle.text = response.body()?.title.toString()
//
//                Log.d("here", "title is ${response.body()?.title}")
//                Log.d("here", "body is ${response.body()?.body}")
//            }
//            else
//            {
//                Log.d("here", "title is ${response.errorBody().toString()}")
//
//            }
//        })
    }
}