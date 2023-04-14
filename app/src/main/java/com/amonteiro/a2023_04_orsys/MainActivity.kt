package com.amonteiro.a2023_04_orsys

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.amonteiro.a2023_04_orsys.databinding.ActivityMainBinding

const val MENU_METEO = 5
const val MENU_RECYCLERVIEW = 6
const val MENU_METEO_AROUND = 7

class MainActivity : AppCompatActivity() {

    //Créer composants graphiques
    //by lazy action a retardement -> à la 1ere utilisation
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //afficher
        setContentView(binding.root)

        println("MainActivity.onCreate")

        //Intercepter les clics des boutons
        binding.btValidate.setOnClickListener {

            if (binding.rbLike.isChecked) {
                binding.et.setText(binding.rbLike.text)
            }
            else if (binding.rbDislike.isChecked) {
                binding.et.setText(binding.rbDislike.text)
            }
            binding.iv.setImageResource(R.drawable.baseline_delete_forever_24)

        }

        binding.btCancel.setOnClickListener {
            binding.et.setText("")
            binding.rg.clearCheck()
            binding.iv.setImageResource(R.drawable.baseline_flag_24)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, MENU_METEO, 0, "Météo")
        menu.add(0, MENU_RECYCLERVIEW, 0, "RecyclerView")
        menu.add(0, MENU_METEO_AROUND, 0, "Météo autour")

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == MENU_METEO) {
            val intent = Intent(this, WeatherActivity::class.java)
            //pour envoyer des paramètres
            intent.putExtra("cle", "toto")
            //Lance l'écran
            startActivity(intent)
        }
        else if (item.itemId == MENU_RECYCLERVIEW) {
            val intent = Intent(this, WeatherAroundActivity::class.java)
            //Lance l'écran
            startActivity(intent)
        }
        else if (item.itemId == MENU_METEO_AROUND) {
            val intent = Intent(this, WeatherAroundWithAPIActivity::class.java)
            //Lance l'écran
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }


}