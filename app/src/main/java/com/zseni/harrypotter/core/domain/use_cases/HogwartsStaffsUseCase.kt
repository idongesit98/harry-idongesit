package com.zseni.harrypotter.core.domain.use_cases

import com.zseni.harrypotter.core.data.HarryPotterApi

class HogwartsStaffsUseCase(
    private val harryPotterApi: HarryPotterApi
) {
    suspend operator fun invoke() = harryPotterApi.getStaffs()

}