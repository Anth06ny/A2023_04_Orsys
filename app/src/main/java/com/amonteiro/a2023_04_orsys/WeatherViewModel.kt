package com.amonteiro.a2023_04_orsys

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class WeatherViewModel : ViewModel() {

    var data = MutableLiveData<WeatherBean?>(null)
    var errorMessage = MutableLiveData("")
    var runInProgress = MutableLiveData(false)

    fun loadData(context: Context) {
        //Reset de donnée
        errorMessage.postValue("")
        data.postValue(null)
        runInProgress.postValue(true)

        thread {
            try {

                var lastKnownLocation = LocationUtils.getLastKnownLocation(context) ?: throw Exception("Pas de localisation")

                data.postValue(RequestUtils.loadWeather(lastKnownLocation.latitude, lastKnownLocation.longitude))
            }
            catch (e: Exception) {
                e.printStackTrace()
                errorMessage.postValue(e.message ?: "Une erreur est survenue")
            }
            runInProgress.postValue(false)
        }
    }

    fun loadData(cityName: String) {
        //Reset de donnée
        errorMessage.postValue("")
        data.postValue(null)
        runInProgress.postValue(true)

        thread {
            try {
                data.postValue(RequestUtils.loadWeather(cityName))
            }
            catch (e: Exception) {
                e.printStackTrace()
                errorMessage.postValue(e.message ?: "Une erreur est survenue")
            }
            runInProgress.postValue(false)
        }
    }

}