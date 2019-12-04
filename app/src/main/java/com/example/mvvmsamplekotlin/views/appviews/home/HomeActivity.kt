package com.example.mvvmsamplekotlin.views.appviews.home

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmsamplekotlin.R
import com.example.mvvmsamplekotlin.databinding.ActivityHomeBinding
import com.example.mvvmsamplekotlin.views.appviews.home.viewmodel.HomeActivityViewModel
import com.example.mvvmsamplekotlin.views.appviews.secondactivity.SecondActivity
import com.example.mvvmsamplekotlin.views.baseviews.BaseActivity
import kotlinx.android.synthetic.main.activity_home.view.*
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    var str: String = String()

    var str2: String? = null

    override fun setViewModel(): ViewModel {
        return ViewModelProviders.of(this, factory).get(HomeActivityViewModel::class.java)
    }

    override val layout: Int
        get() = R.layout.activity_home

    override fun initObserver() {
        //do nothing
        viewModel.observeLivedataBefore().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.observeLivedataMeanwhile().observe(this, Observer {
            Toast.makeText(this, "${it.username} & ${it.password}", Toast.LENGTH_SHORT).show()
        })

        viewModel.observeLivedataAfter().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun setUp() {
        //viewModel.sampleAsync()
        binding.root.btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@HomeActivity, SecondActivity::class.java)
                startActivity(intent)
            }

        })
    }

    infix fun show(a: Int): Int {
        return 1
    }

    fun returnStr(): String {
        return "abhi"
    }
}