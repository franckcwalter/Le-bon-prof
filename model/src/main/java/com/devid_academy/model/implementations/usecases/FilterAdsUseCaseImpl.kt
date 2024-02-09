package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.entities.AdDto
import com.devid_academy.domain.usecases.FilterAdsUseCase

class FilterAdsUseCaseImpl : FilterAdsUseCase {

    override fun filterAds(
        adList: List<AdDto>,
        filterByMaxPrice: Int,
        filterByFav: Boolean,
        filterByLocation: String
    ): List<AdDto> {

        return adList.filter {
            (!filterByFav || it.isFav == 1)
                    && (filterByMaxPrice >= 60 || it.price.toDouble() <= filterByMaxPrice.toDouble())
                    && (filterByLocation.isBlank() || it.location.lowercase()
                .startsWith(filterByLocation.lowercase()))
        }
    }
}