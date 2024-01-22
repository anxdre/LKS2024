package com.example.myapplication.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemCardBinding
import com.example.myapplication.model.Product
import org.json.JSONArray

class ProductAdapter(private val onClick: (product: Product) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    var dataset: JSONArray = JSONArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.createView(position)
    }

    override fun getItemCount(): Int = dataset.length()

    fun changeDataset(list: JSONArray) {
        dataset = list
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun createView(position: Int) {
            val data = dataset.getJSONObject(position)
            binding.tvTitle.text = data.getString("title")
            binding.tvDesc.text = "$ ${data.getString("price")}"
            binding.root.setOnClickListener {
                onClick(
                    Product(
                        data.getInt("id"),
                        data.getString("title"),
                        data.getString("description"),
                        data.getInt("price"),
                        data.getString("brand"),
                        data.getString("category")
                    )
                )
            }
        }
    }

}