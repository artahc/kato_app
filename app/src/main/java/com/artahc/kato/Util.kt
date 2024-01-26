package com.artahc.kato

import kotlin.random.Random

fun generateRandomString(length: Int): String {
    val charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
    return (1..length)
        .map { charset[Random.nextInt(charset.length)] }
        .joinToString("")
}
