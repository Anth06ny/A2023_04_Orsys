package com.amonteiro.a2023_04_orsys

import com.google.gson.annotations.SerializedName
import java.util.*

fun main() {

}



/* -------------------------------- */
// API SEloger
/* -------------------------------- */

data class SeLogerBean(
    var center: Center,
    var city: String,
    var country: String,
    var department: String,
    var displayName: String,
    var districts: List<District>,
    var id: String,
    var postalCode: String,
    var region: String,
    var type: Int
)

data class Center(
    var available: Boolean,
    var latitude: Double,
    var longitude: Double
)

data class District(
    var id: Int,
    var name: String
)

/* -------------------------------- */
// API Météo
/* -------------------------------- */
//Ici je n'ai mis que ce qui est utile pour l'affichage demandé mais on peut tout mettre
data class WeatherBean(
    var name: String,
    @SerializedName("main")
    var temperature: TempBean?,
    var wind : WindBean,
    var weather:List<DescriptionBean>,
)

data class TempBean(var temp: Double, var temp_min: Double?, var temp_max: Double?)
data class DescriptionBean(var description: String, var icon: String)
data class WindBean(var speed: Double)

data class ErrorBean(var message:String?)

/* -------------------------------- */
// EXO
/* -------------------------------- */

class RandomName {
    private val list = arrayListOf("Toto", "Tata", "titi")
    private var saveValue = ""


    fun add(name: String?)= if(!name.isNullOrBlank() && name !in list) list.add(name) else false

    fun next() = list.random()

    fun nextDiff() = list.filter{ it != saveValue }.random().also {
             saveValue = it
         }

    fun next2() = Pair(nextDiff(), nextDiff())

}

class PlaneBean(name: String) {
    var id = name.hashCode()
        private set

    var name = name
        set(value) {
            field = value
            id = name.hashCode()
        }
}

data class UserBean(val name: String, var note: Int = 0) {
    val id = name.hashCode()
}

data class PrintRandomIntBean(val max: Int) {
    val random: Random = Random()

    init {
        println(random.nextInt(max))
        println(random.nextInt(max))
        println(random.nextInt(max))
    }

    constructor() : this(100) {
        println(random.nextInt(max))
    }

}


data class StudentBean(val name: String) {
    var note = 0
}

data class CarBean(var marque: String, var model: String) {
    var couleur = ""
}