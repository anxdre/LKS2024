package com.example.myapplication.api

import com.example.myapplication.model.Product
import java.net.HttpURLConnection
import java.net.URL

object ApiClient {

    val cartOfItem = mutableListOf<Product>()

    fun getData(url: String): String {
        val endpoint = URL(url)
        val urlConnection = endpoint.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "GET"

        if (urlConnection.responseCode != 200) {
            throw RuntimeException("Failed : HTTP error code : " + urlConnection.responseCode)
        }

        try {
            return urlConnection.inputStream.bufferedReader().readText()
        } finally {
            urlConnection.disconnect()
        }
    }
}