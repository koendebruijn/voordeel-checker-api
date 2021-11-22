package com.koendebruijn.voordeelcheckerapi.dto

import java.net.URL

data class Product(
    val name: String,
    val price: Double,
    val oldPrice: Double? = null,
    val priceUnit: String? = null,
    val discount: String,
    val imageUrl: URL,
    val productLink: URL,
    val supermarket: String
)
