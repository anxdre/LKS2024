package com.example.myapplication.view.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.api.ApiClient
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.view.detail.DetailActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding!!
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
//        return super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ProductAdapter {
            startActivity(Intent(requireContext(), DetailActivity::class.java).putExtra("data", it))
        }
        binding.rvTrending.adapter = adapter
        fetchData()
    }

    private fun fetchData() {
        var jsonListOfProduct = JSONArray()
        val job = CoroutineScope(Dispatchers.IO).launch {
            val result = ApiClient.getData("https://dummyjson.com/products")
            val json = JSONObject(JSONTokener(result))
            jsonListOfProduct = json.getJSONArray("products")
        }.invokeOnCompletion {
            CoroutineScope(Dispatchers.Main).launch {
                adapter.changeDataset(jsonListOfProduct)
            }
        }
    }
}