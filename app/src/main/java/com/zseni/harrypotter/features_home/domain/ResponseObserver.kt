package com.zseni.harrypotter.features_home.domain

import androidx.lifecycle.Observer
import com.zseni.harrypotter.core.domain.model.CharactersItem
import retrofit2.Response

class ResponseObserver(
    val onValueChanged:(value: Response<List<CharactersItem>>) -> Unit
):Observer<Response<List<CharactersItem>>> {
    override fun onChanged(value: Response<List<CharactersItem>>) {
        onValueChanged(value)
    }
}