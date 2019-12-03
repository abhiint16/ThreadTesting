package com.example.mvvmsamplekotlin.views.appviews.home.viewmodel

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmsamplekotlin.datamanager.DataManager
import com.example.mvvmsamplekotlin.views.appviews.home.CustomThread
import com.example.mvvmsamplekotlin.views.appviews.home.model.RegistrationRequest
import com.example.mvvmsamplekotlin.views.appviews.home.ThreadCallback
import com.example.mvvmsamplekotlin.views.baseviews.BaseViewModel

class HomeActivityViewModel(dataManager: DataManager) : BaseViewModel(dataManager), ThreadCallback {

    var liveDataBefore = MutableLiveData<String>()
    var liveDataAfter = MutableLiveData<String>()
    var liveDataMeanwhile = MutableLiveData<RegistrationRequest>()

    var customThread = CustomThread(this)

    var handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            Log.e("Check for thead400", "${Thread.currentThread()}")
            var reg = msg.data.getParcelable<RegistrationRequest>("parce")
            liveDataMeanwhile.value = reg
        }

    }

    fun sample() {

        /*var thread = object : Thread() {
            override fun run() {
                liveData.value = "nlearnMobile"
            }
        }*/

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

        /*handler.post(object : Runnable {
            override fun run() {
                Log.e("Check for thead4", "${Thread.currentThread()}")
                liveDataBefore.value = "Task is Starting ${Thread.currentThread()}"
            }
        })*/

    }

    override fun after() {
        // update UI for task has finished
        /*handler.post(object : Runnable {
            override fun run() {
                Log.e("Check for thead5", "${Thread.currentThread()}")
                liveDataAfter.value = "Task is finished ${Thread.currentThread()}"
            }
        })
*/
    }

    override fun meanwhile(registrationRequest: RegistrationRequest) {
        // update UI for task is in progress

        /*handler.post(object : Runnable {
            override fun run() {
                Log.e("Check for thead6", "${Thread.currentThread()}")
                liveDataMeanwhile.value = 0
            }
        })*/

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