package com.sample.dishes.ui.splash

import android.os.Bundle
import androidx.activity.viewModels
import com.sample.dishes.databinding.ActivitySplashScreenBinding
import com.sample.dishes.ui.base.BaseVMActivity
import com.sample.dishes.ui.home.MainActivity
import com.sample.dishes.utils.launchActivity
import com.sample.dishes.vm.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity : BaseVMActivity<SplashViewModel, ActivitySplashScreenBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
    }


    override val mViewModel: SplashViewModel by viewModels()


    override fun getViewBinding(): ActivitySplashScreenBinding =
        ActivitySplashScreenBinding.inflate(layoutInflater)

    override fun initView() {

    }

    override fun initializeObservers() {
        super.initializeObservers()
        mViewModel.mutableLiveData.observe(this, {
            when (it) {
                is SplashViewModel.SplashState.MainActivity -> {
                    launchActivity<MainActivity> { }
                }
            }

        })


    }

}