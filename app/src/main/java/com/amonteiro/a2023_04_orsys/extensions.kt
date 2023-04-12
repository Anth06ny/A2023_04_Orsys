package com.amonteiro.a2023_04_orsys

import com.google.gson.Gson

fun main() {
    val user = UserBean("toto", 18)

    println(user.toJson())

}

fun Any?.toJson() = if(this==null) "{}" else  Gson().toJson(this)