package com.artahc.kato.ui.cart_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artahc.kato.databinding.CartItemTileBinding
import com.artahc.kato.domain.model.CartItem

class CartItemAdapter(
    private var cartItems: List<CartItem>,
    private val onClickCallback: (CartItem) -> Unit
) : RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {
    class ViewHolder(private val binding: CartItemTileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartItem, onClickCallback: (CartItem) -> Unit) {
            binding.cartItemTileProductName.text = item.productName
            binding.cartItemTileQuantity.text = item.quantity.toString()
            binding.cartItemTileProductPrice.text = item.productPriceEach.toString()
            binding.cartItemTileTotalPrice.text =
                (item.quantity * item.productPriceEach).toString()

            binding.root.setOnClickListener {
                onClickCallback(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CartItemTileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = cartItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            item = cartItems[position],
            onClickCallback = onClickCallback
        )
    }

    fun updateCartItems(newCartItems: List<CartItem>) {
        cartItems = newCartItems
        notifyDataSetChanged()
    }
}