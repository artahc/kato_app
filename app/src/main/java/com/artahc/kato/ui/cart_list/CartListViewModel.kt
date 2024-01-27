package com.artahc.kato.ui.cart_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artahc.kato.domain.repository.CartRepository
import com.artahc.kato.domain.model.Cart
import com.artahc.kato.generateRandomString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CartListState {
    data class Loaded(val carts: List<Cart>) : CartListState()
    data object Loading : CartListState()
    data object Error : CartListState()
}

@HiltViewModel
class CartListViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<CartListState>(CartListState.Loading)
    val uiState: StateFlow<CartListState> = _uiState

    init {
        viewModelScope.launch {
            cartRepository.getAllCarts()
                .collect {
                    _uiState.emit(CartListState.Loaded(it))
                }
        }
    }

    fun createNewCart(name: String? = null) {
        val cartName = name ?: generateRandomString(10)
        viewModelScope.launch {
            val cart = cartRepository.createCart(cartName)
            Log.d("CartListViewModel", "$cart")
        }
    }
}