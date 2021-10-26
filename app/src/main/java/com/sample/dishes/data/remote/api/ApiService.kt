package com.sample.dishes.data.remote.api

import com.sample.dishes.model.HomeModelResponses
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("dishesoftheday")
    suspend fun getDishesOfTheDay(): Response<HomeModelResponses>

    @GET("dishes/{dishId}")
    suspend fun getDishInfoDetails(@Path("dishId") dishId: String): Response<HomeModelResponses>
}