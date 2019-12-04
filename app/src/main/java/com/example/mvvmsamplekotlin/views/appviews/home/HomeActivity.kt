package com.example.mvvmsamplekotlin.views.appviews.home

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import android.util.Log
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

        /*var handler = object : Handler() {

            override fun handleMessage(msg: Message) {
                //looper.queue.
            }

        }

        handler.obtainMessage()


        var runnable = object : Runnable {
            override fun run() {

            }
        }

        var handlerThread: HandlerThread = HandlerThread("")*/

        var runnable = object : Runnable {
            override fun run() {
                Log.e("run running200", "runnbale ${Thread.currentThread()}")
            }
        }

        var myThread = MyThread()

        myThread.start()
        Thread.sleep(1000)
        myThread.handler.post(runnable)





        /*var msg: Message = Message()

        //msg.setCallback()

        handler.sendMessage(msg)

        handler.postDelayed(runnable, 5000)

        handler.post(object : Runnable {
            override fun run() {

            }

        })

        viewModel.testFun()*/
    }
}

class MyThread : Thread() {

    var handler = Handler()

    override fun run() {
        Looper.prepare()
        Log.e("run running100", "runnbale ${currentThread()}")
        Looper.loop()
    }
}