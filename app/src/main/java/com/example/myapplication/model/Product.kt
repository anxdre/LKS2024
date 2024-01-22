package com.example.myapplication.model

import java.io.Serializable


data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val brand: String,
    val category: String,
    val images: MutableList<String> = mutableListOf(),
) : Serializable