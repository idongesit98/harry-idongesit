package com.zseni.harrypotter.navigation.screen

import com.zseni.harrypotter.navigation.NavConstants

sealed class Screens(val route:String) {

    object Splash:Screens(route = NavConstants.Splash_Screen_Route)

    object Main:Screens(route = NavConstants.Main_Screen_Route)

    object Detail:Screens(route = "${NavConstants.Detail_Screen_Route}/{model}"){
        fun passCharacterModel(characterModelString:String) =
            "${NavConstants.Detail_Screen_Route}/$characterModelString"
    }

    object Category:Screens(route = "${NavConstants.Category_Screen_Route}/{category}"){
        fun passCategory(category:String) =
            "${NavConstants.Category_Screen_Route}/$category"
    }


}