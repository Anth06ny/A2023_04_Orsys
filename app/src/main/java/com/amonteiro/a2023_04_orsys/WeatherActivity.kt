package com.amonteiro.a2023_04_orsys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.a2023_04_orsys.databinding.ActivityWeatherBinding
import com.squareup.picasso.Picasso
import kotlin.concurrent.thread

class WeatherActivity : AppCompatActivity() {

    //IHM
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }

    //Données
    val model by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //recupérer un paramètre
        //val value = intent.getStringExtra("cle")

        println("data=${model.data}")

        binding.btLoad.setOnClickListener {

            val city = binding.etCityName.text.toString()
            binding.progressBar.isVisible = true
            binding.tvError.isVisible = false

            //Thread séparé
            thread {
                //Récupération de donnée
                model.loadData(city)
                //Affichage
                //Executé sur le thread principale
                runOnUiThread {
                    refreshData()
                    binding.progressBar.isVisible = false
                }
            }
        }

        refreshData()

    }

    fun refreshData() {
        binding.tv.text = model.data?.name ?: "-"
        binding.tvWind.text = "${model.data?.wind?.speed ?: "-"}"
        binding.tvTemp.text = "${model.data?.temperature?.temp ?: "-"}°"
        binding.tvMinMax.text = "(${model.data?.temperature?.temp_min ?: "-"}°/${model.data?.temperature?.temp_max ?: "-"}°)"

        if (model.data?.weather?.isNotEmpty() == true) {
            binding.tvDesc.setText(model.data?.weather?.get(0)?.description)
            Picasso.get().load("https://openweathermap.org/img/wn/${model.data?.weather?.get(0)?.icon}@4x.png")
                .placeholder(R.drawable.baseline_flag_24)
                .error(R.drawable.baseline_delete_forever_24)
                .into(binding.ivTemp)
        }
        else {
            binding.tvDesc.setText("-")
            binding.ivTemp.setImageBitmap(null)
        }
    }
}