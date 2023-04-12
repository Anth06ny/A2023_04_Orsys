package com.amonteiro.a2023_04_orsys

import kotlin.concurrent.thread


fun main() {
    println("Coucou")

    var v3 : String? = null

    var v4: String = v3 + v3
    println(v4)


    boulangerie(nbSand = 5)

}

fun boulangerie(nbCroi:Int = 0, nbBag : Int = 0, nbSand:Int = 0) =
    nbCroi * PRICE_CROISSANT + nbBag * PRICE_BAG + nbSand * PRICE_SAND