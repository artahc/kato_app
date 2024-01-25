package com.artahc.kato.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.artahc.kato.domain.model.Cart
import java.util.UUID

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString(),
    val name: String,
)


fun CartEntity.asModel(): Cart {
    return Cart(id = id, name = name)
}