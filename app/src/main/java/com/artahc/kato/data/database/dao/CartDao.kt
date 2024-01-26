package com.artahc.kato.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.artahc.kato.data.database.entity.CartEntity
import com.artahc.kato.data.database.entity.CartWithItems
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Transaction
    @Query(
        value = """
        SELECT * FROM cart
    """
    )
    fun getAllCarts(): Flow<List<CartWithItems>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCart(cart: CartEntity): Long

    @Query(
        value = """
        DELETE FROM cart 
        WHERE id = :id
    """
    )
    fun deleteCart(id: String)
}