package com.example.mvvmsamplekotlin.views.appviews.home

import android.os.Handler
import android.os.Message
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmsamplekotlin.R
import com.example.mvvmsamplekotlin.databinding.ActivityHomeBinding
import com.example.mvvmsamplekotlin.views.appviews.home.viewmodel.HomeActivityViewModel
import com.example.mvvmsamplekotlin.views.baseviews.BaseActivity
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun setViewModel(): ViewModel {
        return ViewModelProviders.of(this, factory).get(HomeActivityViewModel::class.java)
    }

    override val layout: Int
        get() = R.layout.activity_home

    override fun initObserver() {
        viewModel.observeForLiveData().observe(this, Observer { boolean ->
            Toast.makeText(this, "Live Data Observed", Toast.LENGTH_LONG).show()
        })
    }

    override fun setUp() {

        var handler = Handler()

        var runnable = object :Runnable {
            override fun run() {

            }
        }

        var msg : Message = Message()

        handler.sendMessage(msg)

        handler.postDelayed(runnable, 5000)

        handler.post(object : Runnable{
            override fun run() {

            }

        })

        viewModel.testFun()
    }
}