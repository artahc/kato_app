package com.artahc.kato.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.artahc.kato.data.model.Cart

class CartRepositoryImpl : CartRepository {
    private val carts = MutableLiveData<List<Cart>>()

    override fun getCarts(): LiveData<List<Cart>> {
        return carts
    }

    override fun createCart(name: String) {
        with(carts.value ?: emptyList()) {
            val newCarts = this.toMutableList().apply {
                add(Cart("random", name))
            }
            carts.postValue(newCarts)
        }
    }
}