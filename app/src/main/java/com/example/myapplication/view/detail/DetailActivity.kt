package com.example.myapplication.view.detail

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.api.ApiClient
import com.example.myapplication.databinding.ActivityDetailBinding
import com.example.myapplication.model.Product

class DetailActivity : AppCompatActivity() {
    private lateinit var data: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            data = intent.getSerializableExtra("data") as Product
        } catch (e: Exception) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.tvDesc.text = data.description
        Log.d("data", data.toString())

        binding.btnAdd.setOnClickListener {
            ApiClient.cartOfItem.add(data)
            Toast.makeText(this, "Success Add to cart", Toast.LENGTH_SHORT).show()
        }
    }
}