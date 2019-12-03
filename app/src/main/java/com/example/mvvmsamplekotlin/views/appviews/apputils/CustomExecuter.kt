package com.example.mvvmsamplekotlin.views.appviews.apputils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

class CustomExecuter : Executor {

    var handler = Handler(Looper.getMainLooper())

    override fun execute(runnable: Runnable) {
        handler.post(runnable)
    }

}