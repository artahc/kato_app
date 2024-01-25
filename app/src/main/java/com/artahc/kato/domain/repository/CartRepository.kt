package com.artahc.kato.domain.repository

import com.artahc.kato.domain.model.Cart
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    val allCarts: Flow<List<Cart>>

    suspend fun createCart(name: String): Cart

    suspend fun deleteCart(id: String)
}