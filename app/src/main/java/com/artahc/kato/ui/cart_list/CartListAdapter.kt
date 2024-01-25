package com.artahc.kato.ui.cart_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artahc.kato.databinding.CartItemBinding
import com.artahc.kato.domain.model.Cart

class CartListAdapter(private var carts: List<Cart>) :
    RecyclerView.Adapter<CartListAdapter.ViewHolder>() {
    class ViewHolder(private val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cart: Cart) {
            binding.cartItemName.text = cart.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cart = carts[position]
        holder.bind(cart)
    }

    override fun getItemCount(): Int = carts.size

    fun updateCarts(cartList: List<Cart>) {
        this.carts = cartList
        notifyDataSetChanged()
    }
}