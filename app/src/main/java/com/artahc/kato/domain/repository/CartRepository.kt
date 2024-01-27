package com.artahc.kato.domain.repository

import com.artahc.kato.domain.model.Cart
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun createCart(name: String): Cart

    suspend fun addItemToCart(
        cartId: Long,
        productName: String,
        productPriceEach: Double,
        quantity: Double,
    )

    suspend fun updateCartItem(
        cartId: Long,
        itemId: Long,
        productName: String? = null,
        productPriceEach: Double? = null,
        quantity: Double? = null,
    )

    suspend fun deleteItemFromCart(cartId: Long, itemId: Long)

    suspend fun deleteCart(id: String)

    suspend fun getCart(id: Long): Flow<Cart>

    suspend fun getAllCarts(): Flow<List<Cart>>
}