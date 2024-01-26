package com.artahc.kato.data.repository

import com.artahc.kato.data.database.dao.CartDao
import com.artahc.kato.data.database.entity.CartEntity
import com.artahc.kato.data.database.entity.asModel
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

    override val allCarts: Flow<List<Cart>> = cartDao
        .getAllCarts()
        .map { cartEntities ->
            cartEntities.map { it.asModel() }
        }

    override suspend fun createCart(name: String): Cart {
        val entity = CartEntity(name = name)

        withContext(dispatcher) {
            cartDao.insertCart(entity)
        }

        return Cart(entity.id, name)
    }

    override suspend fun deleteCart(id: String) {
        withContext(dispatcher) {
            cartDao.deleteCart(id)
        }
    }
}