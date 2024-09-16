package com.example.netflix.ui.screen.discover

import com.example.netflix.data.entities.discovere.DiscoverData
import com.example.netflix.data.repositories.DiscoverDataRepo
import javax.inject.Inject
import com.example.netflix.util.Result

class DiscoverDataUseCase @Inject constructor(private val discoverDataRepo: DiscoverDataRepo){

   suspend operator fun invoke() : Result<List<DiscoverData>> //: return ResponseClass?/EntityClass/YourDomainLayerModel/FlowOfData
    {
        return discoverDataRepo.getDiscoverData()
    }
}