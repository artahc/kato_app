package com.artahc.kato.ui.cart_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artahc.kato.domain.repository.CartRepository
import com.artahc.kato.domain.model.Cart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

sealed class CartListState {
    data class Loaded(val carts: List<Cart>) : CartListState()
    data object Loading : CartListState()
    data object Error : CartListState()
}

@HiltViewModel
class CartListViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {
    val allCarts: Flow<CartListState> = cartRepository.allCarts
        .map { CartListState.Loaded(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CartListState.Loading
        )

    fun createNewCart(name: String) {
        viewModelScope.launch {
            val cart = cartRepository.createCart(name)
            Log.d("CartListViewModel", "$cart")
        }
    }

}

fun generateRandomString(length: Int): String {
    val charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
    return (1..length)
        .map { charset[Random.nextInt(charset.length)] }
        .joinToString("")
}
