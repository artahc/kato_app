package com.artahc.kato.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artahc.kato.domain.repository.CartRepository
import com.artahc.kato.domain.model.Cart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed interface HomeUiState {
    data class Loaded(
        val carts: List<Cart>,
        val totalExpensesThisMonth: Double
    ) : HomeUiState

    data object Loading : HomeUiState

    data object Error : HomeUiState
}


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {
    val state: StateFlow<HomeUiState> =
        cartRepository.allCarts
            .map {
                val totalExpensesThisMonth = 0.0

                return@map HomeUiState.Loaded(
                    carts = it,
                    totalExpensesThisMonth = totalExpensesThisMonth
                )
            }
            .stateIn(
                scope = viewModelScope,
                initialValue = HomeUiState.Loading,
                started = SharingStarted.WhileSubscribed(5_000)
            )

    init {
        viewModelScope.launch {
            cartRepository
        }
    }

    fun createNewCart() {

    }
}