package com.artahc.kato.data.repository

import com.artahc.kato.data.database.dao.CartDao
import com.artahc.kato.data.database.entity.CartEntity
import com.artahc.kato.data.database.entity.CartItemEntity
import com.artahc.kato.data.database.entity.toDomain
import com.artahc.kato.domain.model.Cart
import com.artahc.kato.domain.repository.CartRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao,
    private val dispatcher: CoroutineDispatcher,
) : CartRepository {

    override suspend fun createCart(name: String): Cart {
        return withContext(dispatcher) {
            val entity = CartEntity(name = name)
            val cartId = cartDao.insertCart(entity)

            Cart(
                id = cartId,
                name = name,
                items = emptyList(),
            )
        }
    }

    override suspend fun addItemToCart(
        cartId: Long,
        productName: String,
        productPriceEach: Double,
        quantity: Double
    ) {
        withContext(dispatcher) {
            val entity = CartItemEntity(
                quantity = quantity,
                cartId = cartId,
                productName = productName,
                productPriceEach = productPriceEach
            )
            cartDao.insertCartItem(entity)
        }
    }

    override suspend fun updateCartItem(
        cartId: Long,
        itemId: Long,
        productName: String?,
        productPriceEach: Double?,
        quantity: Double?
    ) {
        withContext(dispatcher) {
            cartDao.updateCartItem(
                cartId = cartId,
                itemId = itemId,
                productName = productName,
                productPriceEach = productPriceEach,
                quantity = quantity
            )
        }
    }

    override suspend fun deleteItemFromCart(cartId: Long, itemId: Long) {
        withContext(dispatcher) {
            cartDao.deleteCartItem(cartId = cartId, itemId = itemId)
        }
    }

    override suspend fun deleteCart(id: String) {
        withContext(dispatcher) {
            cartDao.deleteCart(id)
        }
    }

    override suspend fun getCart(id: Long): Flow<Cart> {
        return withContext(dispatcher) {
            cartDao.getCart(id = id).map { it.toDomain() }
        }
    }

    override suspend fun getAllCarts(): Flow<List<Cart>> {
        return withContext(dispatcher) {
            cartDao.getAllCarts().map { result ->
                result.map { entity ->
                    entity.toDomain()
                }
            }
        }
    }
}