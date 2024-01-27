package com.artahc.kato.ui.cart_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.artahc.kato.R
import com.artahc.kato.databinding.FragmentCartDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [CartDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CartDetailFragment : Fragment() {
    private var _binding: FragmentCartDetailBinding? = null
    private val binding get() = _binding!!

    private val args: CartDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: CartDetailViewModel.AssistedFactory

    private val viewModel: CartDetailViewModel by viewModels {
        CartDetailViewModel.provideFactory(viewModelFactory, args.cartId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartDetailBinding.inflate(
            LayoutInflater.from(inflater.context),
            container,
            false
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is CartDetailState.Loaded -> {
                        binding.cartDetailTitle.text = state.cart.name ?: ""
                    }

                    CartDetailState.Loading -> {
                        binding.cartDetailTitle.text = "Loading"
                    }
                }
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CartDetailFragment()
    }
}