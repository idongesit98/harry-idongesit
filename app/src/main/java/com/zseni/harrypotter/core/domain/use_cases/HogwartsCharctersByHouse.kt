package com.zseni.harrypotter.core.domain.use_cases

import com.zseni.harrypotter.core.data.HarryPotterApi

class HogwartsCharactersByHouse(
    private val harryPotterApi: HarryPotterApi
) {
    suspend operator fun invoke(houseName:String) =harryPotterApi.getHouses(houseName)
}