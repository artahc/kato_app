package com.artahc.kato.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.artahc.kato.domain.model.Cart
import com.artahc.kato.domain.model.CartItem

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String? = null,
)

@Entity(tableName = "cart_item")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val cartId: Long,
    val quantity: Double,
    val productName: String,
    val productPriceEach: Double,
)

data class CartWithItems(
    @Embedded
    val cart: CartEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "cartId"
    )
    val items: List<CartItemEntity>
)


fun CartWithItems.toDomain(): Cart {
    return Cart(
        id = cart.id,
        name = cart.name,
        items = items.map {
            CartItem(
                id = it.id,
                productName = it.productName,
                productPriceEach = it.productPriceEach,
                quantity = it.quantity,
            )
        }
    )
}


//@Entity(tableName = "shop")
//data class ShopEntity(
//    @PrimaryKey(autoGenerate = true)
//    val id: Long = 0,
//    val name: String,
//)
//

//
//
//data class ShopWithProducts(
//    @Embedded
//    val shop: ShopEntity,
//
//    @Relation(parentColumn = "id", entityColumn = "shopId")
//    val products: List<ProductEntity>
//)