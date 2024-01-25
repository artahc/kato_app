package com.artahc.kato.data

import androidx.lifecycle.LiveData
import com.artahc.kato.model.Cart

interface CartRepository {
    fun getCarts(): LiveData<Cart>

    fun createCart(name: String)
}