package com.amonteiro.a2023_04_orsys

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

const val URL_API_WEATHER =
    "https://api.openweathermap.org/data/2.5/weather?appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr&q="
const val URL_API_WEATHER_LAT_LNG =
    "https://api.openweathermap.org/data/2.5/weather?appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr"

object RequestUtils {

    val client = OkHttpClient()

    val gson = Gson()


    //Méthode qui prend en entrée une url, execute la requête
    //Retourne le code HTML/JSON reçu
    fun getSeLogerLocation(city: String): List<SeLogerBean> {
        val url = "https://seloger.p.rapidapi.com/locations/search?searchTerm=$city"
        println("url : $url")
        //Création de la requête
        val request = Request.Builder()
            .url(url)
            .get()
            .addHeader("X-RapidAPI-Key", "93329c7cf9msha136bd696cd1040p10a1dejsnbc52cdb0746e")
            .addHeader("X-RapidAPI-Host", "seloger.p.rapidapi.com")
            .build()

        //Execution de la requête
        val json = client.newCall(request).execute().use { //it:Response
            //use permet de fermer la réponse qu'il y ait ou non une exception
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            //Résultat de la requête
            it.body.string()
        }

        return gson.fromJson(json, Array<SeLogerBean>::class.java).toList()
    }

    fun loadWeather(city: String): WeatherBean {
        val json = sendGet(URL_API_WEATHER + city)
        return gson.fromJson(json, WeatherBean::class.java)
    }

    fun loadWeather(latitude: Double, longitude: Double): WeatherBean {
        val json = sendGet("$URL_API_WEATHER_LAT_LNG&lat=$latitude&lon=$longitude")
        return gson.fromJson(json, WeatherBean::class.java)
    }


    //Méthode qui prend en entrée une url, execute la requête
    //Retourne le code HTML/JSON reçu
    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
        //Execution de la requête
        return client.newCall(request).execute().use { //it:Response
            //use permet de fermer la réponse qu'il y ait ou non une exception
            //Analyse du code retour
            if (!it.isSuccessful) {
                val json = it.body.string()
                if (json.contains("{") && json.contains("}")) {
                    //val error = gson.fromJson(json, ErrorBean::class.java)
                    throw Exception("Réponse du serveur incorrect :$json")
                }

                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }
}