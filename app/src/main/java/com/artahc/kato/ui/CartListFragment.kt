package com.artahc.kato.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.artahc.kato.R
import com.artahc.kato.data.CartRepositoryImpl
import com.artahc.kato.databinding.FragmentCartListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [CartListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartListFragment : Fragment() {
    private var _binding: FragmentCartListBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartListBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CartListFragment()
    }
}