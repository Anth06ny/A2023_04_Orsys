package com.amonteiro.a2023_04_orsys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.amonteiro.a2023_04_orsys.databinding.ActivityWeatherAroundBinding
import com.amonteiro.a2023_04_orsys.databinding.ActivityWeatherBinding

class WeatherAroundActivity : AppCompatActivity() {

    //IHM
    val binding by lazy { ActivityWeatherAroundBinding.inflate(layoutInflater) }

    //Données
    val model by lazy { ViewModelProvider(this).get(WeatherAroundViewModel::class.java) }

    //outils
    val adapter = WindListAdapter()

    var count = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rv.adapter =adapter
        binding.rv.layoutManager = GridLayoutManager(this, 2)

        binding.btAdd.setOnClickListener {
            //modif des données
            model.list.add(0,WindBean(count++))
            //modif graphique
            refreshScreen()
            adapter.submitList(model.list.toList())

        }
        binding.btDelete.setOnClickListener {
            model.list.removeFirstOrNull()

            //modif graphique
            refreshScreen()
            adapter.submitList(model.list.toList())
        }

        refreshScreen()
        adapter.submitList(model.list.toList())
    }

    fun refreshScreen(){
        binding.textView.text = model.list.joinToString("\n") { "-$it" }
    }
}