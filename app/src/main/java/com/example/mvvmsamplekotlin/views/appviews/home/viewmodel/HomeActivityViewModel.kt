package com.example.mvvmsamplekotlin.views.appviews.home.viewmodel

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmsamplekotlin.datamanager.DataManager
import com.example.mvvmsamplekotlin.views.appviews.apputils.DefaultExecutorSupplier
import com.example.mvvmsamplekotlin.views.appviews.home.CustomThread
import com.example.mvvmsamplekotlin.views.appviews.home.ThreadCallback
import com.example.mvvmsamplekotlin.views.appviews.home.model.RegistrationRequest
import com.example.mvvmsamplekotlin.views.baseviews.BaseViewModel

class HomeActivityViewModel(dataManager: DataManager) : BaseViewModel(dataManager), ThreadCallback {

    var liveDataBefore = MutableLiveData<String>()
    var liveDataAfter = MutableLiveData<String>()
    var liveDataMeanwhile = MutableLiveData<RegistrationRequest>()

    var customThread = CustomThread(this)

    var handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            Log.e("Check for thead400", "${Thread.currentThread()}")
            val reg = msg.data.getParcelable<RegistrationRequest>("parce")
            liveDataMeanwhile.value = reg
        }

    }

    fun sampleThreadPool() {

        for (i in 1..100) {
            var a = DefaultExecutorSupplier.getInstance().forBackgroundTasks()
                .execute(object : Runnable {
                    override fun run() {
                        for (j in 1..100)
                            Log.e("test pool", "test pool $i && $j ${Thread.currentThread()}")
                    }
                })

            var b = DefaultExecutorSupplier.getInstance().forBackgroundTasks()
                .submit(object : Runnable {
                    override fun run() {
                        for (j in 1..100)
                            Log.e("test pool", "test pool $i && $j ${Thread.currentThread()}")
                    }
                })
        }

    }

    fun sample() {

        customThread.start()
        Thread.sleep(1000)

        val bundle = Bundle()
        bundle.putString("name", "abhi")
        bundle.putString("pass", "1234")

        val msg = Message()

        msg.data = bundle

        customThread.handler.sendMessage(msg)

    }

    override fun before() {
        // update UI for task is starting
    }

    override fun after() {
        // update UI for task is finished
    }

    override fun meanwhile(registrationRequest: RegistrationRequest) {

        val bundle = Bundle()
        bundle.putParcelable("parce", registrationRequest)

        val msg = Message()

        msg.data = bundle

        Log.e("Check for thead40", "${Thread.currentThread()}")
        handler.sendMessage(msg)

    }


    fun observeLivedataBefore(): LiveData<String> {
        return liveDataBefore
    }

    fun observeLivedataAfter(): LiveData<String> {
        return liveDataAfter
    }

    fun observeLivedataMeanwhile(): LiveData<RegistrationRequest> {
        return liveDataMeanwhile
    }
}