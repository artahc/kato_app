package com.artahc.kato.data.repository

import com.artahc.kato.data.database.KatoDatabase
import com.artahc.kato.data.database.entity.CartEntity
import com.artahc.kato.data.database.entity.asModel
import com.artahc.kato.domain.model.Cart
import com.artahc.kato.domain.repository.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val db: KatoDatabase
) : CartRepository {

    override val allCarts: Flow<List<Cart>> = db.cartDao()
        .getAllCarts()
        .map { cartEntities ->
            cartEntities.map { it.asModel() }
        }

    override suspend fun createCart(name: String): Cart {
        val entity = CartEntity(name = name)

        withContext(Dispatchers.IO) {
            db.cartDao().insertCart(entity)
        }

        return Cart(entity.id, name)
    }

    override suspend fun deleteCart(id: String) {
        TODO("Not yet implemented")
    }
}