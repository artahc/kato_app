package com.artahc.kato.domain.model

import java.util.Date

data class Cart(
    val id: Long,
    val name: String?,
    val items: List<CartItem>
)

data class CartItem(
    val productName: String,
    val productPriceEach: Double,
    val quantity: Double,
)