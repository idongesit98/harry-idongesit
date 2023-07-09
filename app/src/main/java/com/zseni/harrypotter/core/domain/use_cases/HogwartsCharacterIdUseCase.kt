package com.zseni.harrypotter.core.domain.use_cases

import com.zseni.harrypotter.core.data.HarryPotterApi

class HogwartsCharacterIdUseCase(
    private val harryPotterApi: HarryPotterApi
) {
    suspend operator fun invoke(id:String)= harryPotterApi.getAllCharacters(id)
}