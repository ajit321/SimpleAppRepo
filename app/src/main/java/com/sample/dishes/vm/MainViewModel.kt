package com.sample.dishes.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.dishes.data.local.dao.HomeDao
import com.sample.dishes.data.remote.api.ResponseResult
import com.sample.dishes.data.remote.api.ViewState
import com.sample.dishes.data.remote.utils.shareWhileObserved
import com.sample.dishes.data.repository.interfaceImpl.HomeRepositoryImpl
import com.sample.dishes.model.HomeModelResponses
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val loginRepository: HomeRepositoryImpl
) : ViewModel() {

    private val _homeModelFlow = MutableSharedFlow<ViewState<HomeModelResponses>>()
    val homeModelFlow: SharedFlow<ViewState<HomeModelResponses>> =
        _homeModelFlow.shareWhileObserved(viewModelScope)

    fun getDishesOfTheDay(
    ) {
        viewModelScope.launch {
            _homeModelFlow.emit(ViewState.loading())
            val viewState = when (val responseState =
                loginRepository.getDishesOfTheDay()) {
                is ResponseResult.Success -> {
                    //saveUserData(responseState.data)
                    ViewState.success(responseState.data)
                }
                is ResponseResult.Error -> ViewState.failed(responseState.message)
                is ResponseResult.NetworkException -> ViewState.NetworkFailed(responseState.networkError)
                is ResponseResult.ErrorException -> ViewState.exceptionError(responseState.exception)
                else -> ViewState.exceptionError(responseState.toString())
            }
            _homeModelFlow.emit(viewState)
        }
    }


    fun getDishesDetailsById(id: Int) {
        viewModelScope.launch {
            _homeModelFlow.emit(ViewState.loading())
            val viewState = when (val responseState =
                loginRepository.getDishInfoDetails(id.toString())) {
                is ResponseResult.Success -> {
                    //saveUserData(responseState.data)
                    ViewState.success(responseState.data)
                }
                is ResponseResult.Error -> ViewState.failed(responseState.message)
                is ResponseResult.NetworkException -> ViewState.NetworkFailed(responseState.networkError)
                is ResponseResult.ErrorException -> ViewState.exceptionError(responseState.exception)
                else -> ViewState.exceptionError(responseState.toString())
            }
            _homeModelFlow.emit(viewState)
        }
    }

}