package com.amonteiro.a2023_04_orsys

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class WeatherAroundWithAPIModel : ViewModel() {

    var data = MutableLiveData<List<WeatherBean>>(ArrayList())
    var errorMessage = MutableLiveData("")
    var runInProgress = MutableLiveData(false)

    fun loadData() {
        //Reset de donnée
        errorMessage.postValue("")
        data.postValue(null)
        runInProgress.postValue(true)

        thread {
            try {
                data.postValue(RequestUtils.loadWeatherAround())
            }
            catch (e: Exception) {
                e.printStackTrace()
                errorMessage.postValue(e.message ?: "Une erreur est survenue")
            }
            runInProgress.postValue(false)
        }
    }
}