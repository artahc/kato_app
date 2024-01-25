package com.artahc.kato.ui

import androidx.lifecycle.ViewModel
import com.artahc.kato.data.CartRepository

class CartListViewModel(
    private val cartRepository: CartRepository
) : ViewModel() {
    val cartsLiveData = cartRepository.getCarts()

    fun createNewCart(name: String) {
        cartRepository.createCart(name)
    }
}

class CartListViewModelFactory()

