package com.sample.dishes.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.sample.dishes.app.SimpleApp
import com.sample.dishes.data.local.dao.HomeDao
import com.sample.dishes.data.local.entity.HomeModelEntity
import com.sample.dishes.data.remote.api.ApiService
import com.sample.dishes.data.remote.api.ResponseResult
import com.sample.dishes.data.remote.utils.getResponse
import com.sample.dishes.data.repository.interfaceImpl.HomeRepositoryImpl
import com.sample.dishes.model.HomeModelResponses
import com.sample.dishes.utils.isOnline
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
        private val apiService: ApiService,
        private val simpleDao: HomeDao

) : HomeRepositoryImpl {
    @RequiresApi(Build.VERSION_CODES.M)
    override suspend fun getDishesOfTheDay(): ResponseResult<HomeModelResponses> {
        return try {
            val response =
                    apiService.getDishesOfTheDay().getResponse()
           // saveUserData(response)
            ResponseResult.success(response)
            /*if (isOnline(SimpleApp.CONTEXT)) {
                val response =
                        apiService.getDishesOfTheDay().getResponse()
                saveUserData(response)
                ResponseResult.success(response)
            } else {
                val data = simpleDao.getDataFormTable()
                ResponseResult.success(HomeModelResponses(
                        id = data.id,
                        image_url = data.image_url,
                        more_images = data.more_images,
                        name = data.name,
                        share_link = data.share_link,
                        short_desc = data.short_desc,
                        wiki_link = data.short_desc
                ))
            }*/


            /**
             * TODO: This when condition to check success or failed case
             * */
            /* when (response.success) {
                 true -> ResponseResult.success(response)
                 else -> ResponseResult.error(response.error)
             }*/
        } catch (e: HttpException) {
            ResponseResult.networkError(e.message().toString())
        } catch (connection: java.net.ConnectException) {
            ResponseResult.networkError(connection.toString())
        } catch (e: Exception) {
            ResponseResult.exception(e.message.toString())
        }
    }

    private suspend fun saveUserData(data: HomeModelResponses) {
        simpleDao.insertIntoTable(HomeModelEntity(id = data.id,
                image_url = data.image_url,
                more_images = data.more_images,
                name = data.name,
                share_link = data.share_link,
                short_desc = data.short_desc,
                wiki_link = data.short_desc
        ))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override suspend fun getDishInfoDetails(dishId: String): ResponseResult<HomeModelResponses> {
        return try {
            val response =
                    apiService.getDishInfoDetails(dishId).getResponse()
            ResponseResult.success(response)
            /*if (isOnline(SimpleApp.CONTEXT)) {
                val response =
                        apiService.getDishInfoDetails(dishId).getResponse()
                ResponseResult.success(response)
            } else {
                val data = simpleDao.getDataFormTable()
                ResponseResult.success(HomeModelResponses(
                        id = data.id,
                        image_url = data.image_url,
                        more_images = data.more_images,
                        name = data.name,
                        share_link = data.share_link,
                        short_desc = data.short_desc,
                        wiki_link = data.short_desc
                ))
            }*/
            /**
             * TODO: This when condition to check success or failed case
             * */
            /*when (response.success) {
                true -> ResponseResult.success(response)
                else -> ResponseResult.error(response.error)
            }*/
        } catch (e: HttpException) {
            ResponseResult.networkError(e.message().toString())
        } catch (connection: java.net.ConnectException) {
            ResponseResult.networkError(connection.toString())
        } catch (e: Exception) {
            ResponseResult.exception(e.message.toString())
        }
    }

}