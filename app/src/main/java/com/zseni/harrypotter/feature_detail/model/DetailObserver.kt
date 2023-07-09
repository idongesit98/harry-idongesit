package com.zseni.harrypotter.feature_detail.model

import androidx.lifecycle.Observer
import com.zseni.harrypotter.core.domain.model.CharactersItem
import retrofit2.Response


class DetailObserver(
    val onValueChanged:(value:Response<CharactersItem>) -> Unit
):Observer<Response<CharactersItem>> {
    override fun onChanged(value: Response<CharactersItem>) {
        onValueChanged(value)
    }
}