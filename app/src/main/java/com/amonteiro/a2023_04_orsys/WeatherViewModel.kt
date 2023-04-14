package com.amonteiro.a2023_04_orsys

import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import com.squareup.picasso.Picasso

class WeatherViewModel : ViewModel() {

    var data : WeatherBean? =null
    var errorMessage = ""

    fun loadData(cityName : String){
        //Reset de donnée
        errorMessage = ""
        data = null

        try {
            data = RequestUtils.loadWeather(cityName)
        }
        catch (e: Exception) {
            e.printStackTrace()
           errorMessage = e.message ?: "Une erreur est survenue"
        }
    }

}