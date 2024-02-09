package com.devid_academy.domain.usecases

import com.devid_academy.domain.entities.AdDto

interface FilterAdsUseCase {
    fun filterAds(
        adList: List<AdDto>,
        filterByMaxPrice: Int,
        filterByFav: Boolean,
        filterByLocation: String
    ): List<AdDto>
}