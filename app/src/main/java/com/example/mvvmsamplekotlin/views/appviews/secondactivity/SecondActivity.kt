package com.example.mvvmsamplekotlin.views.appviews.secondactivity

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmsamplekotlin.R
import com.example.mvvmsamplekotlin.views.appviews.home.viewmodel.HomeActivityViewModel
import java.lang.ref.WeakReference

class SecondActivity : AppCompatActivity() {

    lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        text = findViewById(R.id.text)

        MyAsyncTask(this).execute()
        //MyAsyncTask().execute()
    }
}

class MyAsyncTask() : AsyncTask<Void, Int, Float>() {

    constructor(activity: SecondActivity) : this() {
        weak = WeakReference(activity)
        //strong = activity
    }

    lateinit var weak: WeakReference<SecondActivity>
    //lateinit var strong: SecondActivity


    override fun doInBackground(vararg params: Void?): Float {
        for (i in 0..10000000) {
            Log.e("see thread $i", "see thread ${Thread.currentThread()}")
            publishProgress(i)
        }
        return 0F
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        if (weak.get() == null || weak.get()!!.isFinishing)
            return
        //strong.text.text = values.get(0).toString()
        weak.get()!!.text.text = values.get(0).toString()
        //weak.get()!!.findViewById<TextView>(R.id.text).text = values.get(0).toString()
    }

    override fun onPostExecute(result: Float?) {
        super.onPostExecute(result)
    }

    override fun onCancelled(result: Float?) {
        super.onCancelled(result)
    }

    override fun onCancelled() {
        super.onCancelled()
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }
}