package com.sample.dishes.data.repository.interfaceImpl

import com.sample.dishes.data.remote.api.ResponseResult
import com.sample.dishes.model.HomeModelResponses
import javax.inject.Singleton

@Singleton
interface HomeRepositoryImpl {

    suspend fun getDishesOfTheDay(): ResponseResult<HomeModelResponses>

    suspend fun getDishInfoDetails(dishId:String):ResponseResult<HomeModelResponses>
}