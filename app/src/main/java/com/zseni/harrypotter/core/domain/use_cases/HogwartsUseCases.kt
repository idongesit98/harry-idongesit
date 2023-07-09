package com.zseni.harrypotter.core.domain.use_cases

data class HogwartsUseCases(
    val hogwartsCharacterIdUseCase:HogwartsCharacterIdUseCase,
    val hogwartsCharacter:HogwartsCharacters,
    val hogwartsAllStaff:HogwartsStaffsUseCase,
    val hogwartsSpells: HogwartsSpells,
    val hogwartsStudents: HogwartsStudents,
    val hogwartsCharacterByHouse:HogwartsCharactersByHouse
)