package com.example.netflix.ui.screen.discover

import com.example.netflix.data.entities.discovere.DiscoverData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface DiscoverDataApi {
    @GET("/getAll")
   suspend fun doDiscoverData(): Response<List<DiscoverData>>
}