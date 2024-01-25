package com.artahc.kato.data

import androidx.lifecycle.LiveData
import com.artahc.kato.data.model.Cart

interface CartRepository {
    fun getCarts(): LiveData<List<Cart>>

    fun createCart(name: String)
}