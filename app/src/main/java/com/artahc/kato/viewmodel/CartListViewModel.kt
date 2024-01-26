package com.artahc.kato.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artahc.kato.domain.repository.CartRepository
import com.artahc.kato.domain.model.Cart
import com.artahc.kato.generateRandomString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
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