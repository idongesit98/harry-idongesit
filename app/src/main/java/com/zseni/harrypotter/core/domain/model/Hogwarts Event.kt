package com.zseni.harrypotter.core.domain.model

/**
 * Hogwarts Events that handles every events the apps
 * is meant to handle.
 * You have to think of everything(events) the app will do
 */

sealed class HogwartsEvent {
    // Get Characters by Id
    data class HogwartsCharacterId(val id:String): HogwartsEvent()
    // Get hogwarts by house
    data class GetHogwartsCharactersByHouse(val houseName:String): HogwartsEvent()
    // Get Hogwarts Character
    object GetHogwartsCharacter: HogwartsEvent()
    //Get Hogwarts Students
    object GetHogwartsStudents: HogwartsEvent()
    // Get Hogwarts Spells
    object GetHogwartsSpells: HogwartsEvent()
    // Get Hogwarts Staff
    object GetHogwartsStaff: HogwartsEvent()

    data class SearchCharacters(
        val query:String,
        val allCharacters: List<CharactersItem>,
        val filteredCharacter:(characters:List<CharactersItem>)-> Unit
    ): HogwartsEvent()




}