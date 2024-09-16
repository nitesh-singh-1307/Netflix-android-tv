package com.example.netflix.data.repositories

import com.example.netflix.data.entities.discovere.DiscoverData
import com.example.netflix.util.Result
import javax.inject.Singleton


interface DiscoverDataRepo {
    suspend fun getDiscoverData(): Result<List<DiscoverData>>
}