package com.amonteiro.a2023_04_orsys

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.amonteiro.a2023_04_orsys.databinding.ActivityWeatherBinding
import com.squareup.picasso.Picasso
import kotlin.concurrent.thread

class WeatherActivity : AppCompatActivity() {

    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //recupérer un paramètre
        //val value = intent.getStringExtra("cle")

        binding.btLoad.setOnClickListener {

            val city = binding.etCityName.text.toString()
            binding.progressBar.isVisible = true
            binding.tvError.isVisible = false

            //Thread séparé
            thread {
                try {
                    val weather = RequestUtils.loadWeather(city)
                    //Executé sur le thread principale
                    runOnUiThread {
                        binding.tv.text = weather.name
                        binding.tvWind.text = "${weather.wind.speed}"
                        binding.tvTemp.text = "${weather.temperature?.temp ?: "-"}°"
                        binding.tvMinMax.text = "(${weather.temperature?.temp_min ?: "-"}°/${weather.temperature?.temp_max ?: "-"}°)"

                        if (weather.weather.isNotEmpty()) {
                            binding.tvDesc.setText(weather.weather[0].description)
                            Picasso.get().load("https://openweathermap.org/img/wn/${weather.weather[0].icon}@4x.png")
                                .placeholder(R.drawable.baseline_flag_24)
                                .error(R.drawable.baseline_delete_forever_24)
                                .into(binding.ivTemp)
                        }
                        else {
                            binding.tvDesc.setText("-")
                            binding.ivTemp.setImageBitmap(null)
                        }
                        binding.progressBar.isVisible = false
                        Log.i("System.out", "coucou")
                        println("coucou")
                    }
                }
                catch (e: Exception) {
                    e.printStackTrace()
                    //Met à jour mes composants graphique
                    runOnUiThread {
                        binding.tvError.text = e.message
                        binding.tvError.isVisible = true
                        binding.progressBar.isVisible = false
                    }
                }
            }
        }
    }
}