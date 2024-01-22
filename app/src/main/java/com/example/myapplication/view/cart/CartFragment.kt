package com.example.myapplication.view.cart

import android.R.attr.data
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.api.ApiClient
import com.example.myapplication.databinding.FragmentCartBinding


class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var _binding: FragmentCartBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnCheckout.setOnClickListener {
            ApiClient.cartOfItem.clear()
            Toast.makeText(requireContext(), "Success Checkout", Toast.LENGTH_SHORT).show()
        }

        val service = mutableListOf<Map<String,String>>()
        service.add(mapOf(Pair("JNE","Harga Rp.18000")))
        service.add(mapOf(Pair("Tiki","Harga Rp.18000")))
        service.add(mapOf(Pair("PoS","Harga Rp.18000")))


        val adapter = SimpleAdapter(
            requireContext(),
            service,
            android.R.layout.simple_list_item_2,
            arrayOf("First Line", "Second Line"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        binding.spCart.adapter = adapter
    }
}