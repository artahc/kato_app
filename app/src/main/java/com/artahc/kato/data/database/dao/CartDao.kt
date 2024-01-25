package com.artahc.kato.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.artahc.kato.data.database.entity.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query(
        value = """
        SELECT * FROM cart
    """
    )
    fun getAllCarts(): Flow<List<CartEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCart(cart: CartEntity)

    @Query(
        value = """
        DELETE FROM cart 
        WHERE id = :id
    """
    )
    fun deleteCart(id: String)
}