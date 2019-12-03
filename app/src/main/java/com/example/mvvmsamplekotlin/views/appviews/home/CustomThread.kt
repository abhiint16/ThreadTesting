package com.example.mvvmsamplekotlin.views.appviews.home

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import com.example.mvvmsamplekotlin.views.appviews.home.model.RegistrationRequest


class CustomThread(var threadCallback: ThreadCallback) : Thread() {

    lateinit var handler: Handler


    override fun run() {

        Looper.prepare()

        threadCallback.before()

        var m = Looper.myQueue()

        /*Log.e("Check for thead1", "${currentThread()}")

        for (i in 1..10000000) {
            Log.e("Check for thead2", "${currentThread()}")
            if (i == 1000000)
                threadCallback.meanwhile()
        }

        Log.e("Check for thead3", "${currentThread()}")
*/

        handler = object : Handler() {

            override fun handleMessage(msg: Message) {
                var name = msg.data.getString("name")
                var password = msg.data.getString("pass")

                Log.e("Check for thead1", "$name $password ${currentThread()}")

                var registrationRequest =
                    RegistrationRequest()

                registrationRequest.username = name!!
                registrationRequest.password = password!!

                threadCallback.meanwhile(registrationRequest)
            }

        }


        Looper.loop()
        threadCallback.after()

    }




}


interface ThreadCallback {

    fun before()

    fun after()

    fun meanwhile(registrationRequest: RegistrationRequest)

}