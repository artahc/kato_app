package com.artahc.kato.domain.model

data class Cart(
    val id: Long,
    val name: String?,
    val items: List<CartItem>
)

data class CartItem(
    val id: Long,
    val productName: String,
    val productPriceEach: Double,
    val quantity: Double,
)