package com.amonteiro.a2023_04_orsys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.amonteiro.a2023_04_orsys.databinding.ActivityWeatherAroundBinding

class WeatherAroundWithAPIActivity : AppCompatActivity() {

    //IHM
    val binding by lazy { ActivityWeatherAroundBinding.inflate(layoutInflater) }

    //Données
    val model by lazy { ViewModelProvider(this).get(WeatherAroundWithAPIModel::class.java) }

    //outils
    val adapter = WeatherListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rv.adapter = adapter
        binding.rv.layoutManager = GridLayoutManager(this, 2)

        binding.btAdd.setOnClickListener {
            model.loadData()
        }

        model.data.observe(this) {
            adapter.submitList(it)
        }
    }

}