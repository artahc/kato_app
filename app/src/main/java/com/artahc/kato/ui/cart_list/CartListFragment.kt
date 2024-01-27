package com.artahc.kato.ui.cart_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.artahc.kato.databinding.FragmentCartListBinding
import com.artahc.kato.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [CartListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CartListFragment : Fragment() {
    private var _binding: FragmentCartListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CartListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartListBinding.inflate(inflater, container, false)
        val adapter = CartListAdapter(emptyList()) { cart ->
            val action = HomeFragmentDirections.toCartDetail(cartId = cart.id)
            findNavController().navigate(action)
        }
        binding.cartListRecyclerView.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    .onEach { state ->
                        when (state) {
                            CartListState.Error -> println("Error")

                            is CartListState.Loaded -> {
                                adapter.updateCarts(state.carts)
                            }

                            CartListState.Loading -> println("Loading")
                        }
                    }
                    .collect()
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = CartListFragment()
    }
}