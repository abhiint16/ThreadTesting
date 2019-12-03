package com.example.mvvmsamplekotlin.views.appviews.apputils

import android.os.Process
import android.util.Log
import java.util.concurrent.ThreadFactory

class CustomThreadFactory(var threadPriority: Int) : ThreadFactory {


    override fun newThread(r: Runnable): Thread {
        val mRunnbale = object : Runnable {
            override fun run() {
                //Log.e("test pool in","test pool in ${Thread.currentThread()}")
                try {
                    Process.setThreadPriority(threadPriority)
                } catch (t: Throwable) {
                    //do something
                }
                r.run()
            }

        }
        return Thread(mRunnbale)
    }

}