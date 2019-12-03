package com.example.mvvmsamplekotlin.views.appviews.home

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

    var str: String = String()

    var str2: String? = null

    override fun setViewModel(): ViewModel {
        return ViewModelProviders.of(this).get(HomeActivityViewModel::class.java)
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
        viewModel.sample()
    }

    infix fun show(a: Int): Int {
        return 1
    }

    fun returnStr(): String {
        return "abhi"
    }
}