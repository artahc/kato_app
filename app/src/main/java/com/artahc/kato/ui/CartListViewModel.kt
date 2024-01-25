package com.artahc.kato.ui

import androidx.lifecycle.ViewModel
import com.artahc.kato.data.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartListViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {
    val state = cartRepository.getCarts()

    fun createNewCart(name: String) {
        cartRepository.createCart(name)
    }
}
