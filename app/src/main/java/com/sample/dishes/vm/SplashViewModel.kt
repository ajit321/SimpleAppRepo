package com.sample.dishes.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel @ViewModelInject constructor() :
    ViewModel() {

    val mutableLiveData = MutableLiveData<SplashState>()
    private val splashScreenDelay: Long = 2000

    init {
        viewModelScope.launch {
            delay(splashScreenDelay)
            mutableLiveData.postValue(SplashState.MainActivity)
        }
    }

    /**
     * This class is used to show splash state.
     */
    sealed class SplashState {
        /**
         * This object is used to navigate splash screen to main activity.
         */
        object MainActivity : SplashState()

    }
    private fun isUserLoggedIn() = true

}