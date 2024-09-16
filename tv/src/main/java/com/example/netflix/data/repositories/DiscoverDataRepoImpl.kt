package com.example.netflix.data.repositories

import com.example.netflix.data.entities.discovere.DiscoverData
import com.example.netflix.ui.screen.discover.DiscoverDataApi
import javax.inject.Inject
import com.example.netflix.util.Result
import javax.inject.Singleton


@Singleton
class DiscoverDataRepoImpl @Inject constructor(private val discoverDataApi: DiscoverDataApi) : DiscoverDataRepo{
    override suspend fun getDiscoverData(): Result<List<DiscoverData>> {
        return try {
            val response = discoverDataApi.doDiscoverData()
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(Exception("API request failed with code: ${response.code()}"))
            }
        } catch (e: Exception){
            Result.Error(e)
        }
    }
}