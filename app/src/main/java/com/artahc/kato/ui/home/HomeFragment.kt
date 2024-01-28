package com.artahc.kato.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.artahc.kato.databinding.FragmentHomeBinding
import com.artahc.kato.ui.cart_list.CartListFragment
import com.artahc.kato.ui.cart_list.CartListViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CartListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentHomeBinding.inflate(LayoutInflater.from(inflater.context), container, false)

        val cartListFragment = CartListFragment.newInstance()
        parentFragmentManager.beginTransaction()
            .replace(binding.cartListFragmentContainer.id, cartListFragment)
            .commit()

        binding.topFragmentContainer.createNewCartButton.setOnClickListener {
            viewModel.createNewCart()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}