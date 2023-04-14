package com.amonteiro.a2023_04_orsys

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.a2023_04_orsys.databinding.ActivityWeatherBinding
import com.squareup.picasso.Picasso

class WeatherActivity : AppCompatActivity() {

    //IHM
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }

    //Données
    val model by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btLoad.setOnClickListener {
            model.loadData(binding.etCityName.text.toString())
        }

        binding.btMyLoc.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                //On a la permission
                model.loadData(this)
            }
            else  {
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 0)
            }
        }

        observe()
    }

    //Callback de la popup permission
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            //On a la permission
            model.loadData(this)
        }
        else  {
           model.errorMessage.postValue("Il faut la permission")
        }
    }

    fun observe() {

        model.runInProgress.observe(this) {
            binding.progressBar.isVisible = it
        }

        model.errorMessage.observe(this) {
            binding.tvError.text = it
            binding.tvError.isVisible = it.isNotBlank()
        }

        model.data.observe(this) {
            binding.tv.text = it?.name ?: "-"
            binding.tvWind.text = "${it?.wind?.speed ?: "-"}"
            binding.tvTemp.text = "${it?.temperature?.temp ?: "-"}°"
            binding.tvMinMax.text = "(${it?.temperature?.temp_min ?: "-"}°/${it?.temperature?.temp_max ?: "-"}°)"

            if (it?.weather?.isNotEmpty() == true) {
                binding.tvDesc.text = it.weather[0].description
                Picasso.get().load("https://openweathermap.org/img/wn/${it.weather[0].icon}@4x.png")
                    .placeholder(R.drawable.baseline_flag_24)
                    .error(R.drawable.baseline_delete_forever_24)
                    .into(binding.ivTemp)
            }
            else {
                binding.tvDesc.text = "-"
                binding.ivTemp.setImageBitmap(null)
            }
        }
    }

}