package com.artahc.kato.ui.cart_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.artahc.kato.domain.model.Cart
import com.artahc.kato.domain.repository.CartRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

sealed class CartDetailState {
    data object Loading : CartDetailState()
    data class Loaded(
        val cart: Cart
    ) : CartDetailState()
}

class CartDetailViewModel @AssistedInject constructor(
    private val cartRepository: CartRepository,
    @Assisted private val cartId: Long,
) : ViewModel() {
    private val _uiState = MutableStateFlow<CartDetailState>(CartDetailState.Loading)
    val uiState: StateFlow<CartDetailState> = _uiState

    init {
        viewModelScope.launch {
            cartRepository.getCart(cartId)
                .onEach { cart ->
                    _uiState.emit(CartDetailState.Loaded(cart = cart))
                }
                .collect()
        }
    }

    fun addCartItem() {

    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(cartId: Long): CartDetailViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            cartId: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(cartId) as T
            }
        }
    }
}
