package com.amonteiro.a2023_04_orsys

fun main() {

}

fun exo1(){
    val lowerV1 : (String) -> Unit = { it:String-> println(it.lowercase()) }
    val lowerV2  = { it:String-> println(it.lowercase()) }
    val lowerV3 : (String) -> Unit = { it -> println(it.lowercase()) }
    val lowerV4 : (String) -> Unit = { println(it.lowercase()) }

    val hour :(Int)-> Int = {it/60}

    val max  = {a:Int, b : Int -> Math.max(a, b) }

    val reverse : (String)->String = {it.reversed()}

    val minToMinHour : (Int ) -> Pair<Int, Int> = { Pair(it/60, it%60)}

    val users = arrayListOf(UserBean ("toto", 22),
        UserBean ("Charles", 14))


}