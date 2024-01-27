package com.artahc.kato.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.artahc.kato.data.database.entity.CartEntity
import com.artahc.kato.data.database.entity.CartItemEntity
import com.artahc.kato.data.database.entity.CartWithItems
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Transaction
    @Query(
        """
        SELECT * FROM cart
    """
    )
    fun getAllCarts(): Flow<List<CartWithItems>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCart(cart: CartEntity): Long

    @Query(
        """
        DELETE FROM cart 
        WHERE id = :id
    """
    )
    fun deleteCart(id: String)

    @Insert
    fun insertCartItem(cartItem: CartItemEntity): Long

    @Query(
        """
        UPDATE cart_item SET 
        productName = CASE WHEN :productName IS NOT NULL THEN :productName else productName END,
        productPriceEach = CASE WHEN :productPriceEach IS NOT NULL THEN :productPriceEach else productName END,
        quantity = CASE WHEN :quantity IS NOT NULL THEN :quantity else productName END
        WHERe cartId = :cartId AND id = :itemId
    """
    )
    fun updateCartItem(
        cartId: Long,
        itemId: Long,
        productName: String?,
        productPriceEach: Double?,
        quantity: Double?
    )

    @Query(
        """
        DELETE FROM cart_item WHERE id = :itemId and cartId = :cartId
    """
    )
    fun deleteCartItem(cartId: Long, itemId: Long)

    @Query(
        """
        SELECT * FROM cart WHERE id = :id
    """
    )
    fun getCart(id: Long): Flow<CartWithItems>
}