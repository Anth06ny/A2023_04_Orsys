package com.amonteiro.a2023_04_orsys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.amonteiro.a2023_04_orsys.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Créer composants graphiques
    //by lazy action a retardement -> à la 1ere utilisation
    val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //afficher
        setContentView(binding.root)

        //Intercepter les clics des boutons
        binding.btValider.setOnClickListener {
            binding.et.setText("Clic sur valider")
        }

        binding.btCancel.setOnClickListener {
            binding.et.setText("Clic sur Annuler")
        }

    }

}