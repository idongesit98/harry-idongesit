package com.zseni.harrypotter.core.data

import com.zseni.harrypotter.core.domain.model.CharactersItem
import com.zseni.harrypotter.core.domain.model.Spell
import com.zseni.harrypotter.core.presentation.util.Constants.Characters_URL
import com.zseni.harrypotter.core.presentation.util.Constants.Houses_URL
import com.zseni.harrypotter.core.presentation.util.Constants.Spells_URL
import com.zseni.harrypotter.core.presentation.util.Constants.Staffs_URL
import com.zseni.harrypotter.core.presentation.util.Constants.Students_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HarryPotterApi {
    //Get all Characters
    @GET(Characters_URL)
    suspend fun getCharacters():Response<List<CharactersItem>>

    // Get students
    @GET(Students_URL)
    suspend fun getStudents():Response<List<CharactersItem>>

    //Get Staffs
    @GET(Staffs_URL)
    suspend fun getStaffs():Response<List<CharactersItem>>

    //Get Houses by name
    @GET(Houses_URL)
    suspend fun getHouses(@Path("houseName")houseName:String):Response<List<CharactersItem>>

    // Get Spells
    @GET(Spells_URL)
    suspend fun getSpell():Response<List<Spell>>

    @GET(Characters_URL)
    suspend fun getAllCharacters(@Query("id")id:String):Response<CharactersItem>


}