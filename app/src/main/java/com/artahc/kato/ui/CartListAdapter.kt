package com.artahc.kato.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.artahc.kato.databinding.CartItemBinding
import com.artahc.kato.model.Cart

class CartListAdapter(private var cartList: List<Cart>) :
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
        val cart = cartList[position]
        holder.bind(cart)
    }

    override fun getItemCount(): Int = cartList.size

    fun updateData(cartList: List<Cart>) {
        this.cartList = cartList
        notifyDataSetChanged()
    }
}