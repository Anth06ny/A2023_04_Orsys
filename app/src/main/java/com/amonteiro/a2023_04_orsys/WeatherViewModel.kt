package com.amonteiro.a2023_04_orsys

import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.picasso.Picasso
import kotlin.concurrent.thread

class WeatherViewModel : ViewModel() {

    var data = MutableLiveData<WeatherBean?>(null)
    var errorMessage = MutableLiveData("")
    var runInProgress = MutableLiveData(false)

    fun loadData(cityName : String){
        //Reset de donnée
        errorMessage .postValue("")
        data.postValue(null)
        runInProgress.postValue(true)

        thread {
            try {
                data.postValue(RequestUtils.loadWeather(cityName))
            }
            catch (e: Exception) {
                e.printStackTrace()
                errorMessage .postValue(e.message ?: "Une erreur est survenue")
            }
            runInProgress.postValue(false)
        }
    }

}