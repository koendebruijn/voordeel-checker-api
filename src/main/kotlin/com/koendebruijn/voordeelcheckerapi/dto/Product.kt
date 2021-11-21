package com.koendebruijn.voordeelcheckerapi.dto

import java.net.URL

data class Product(
    val name: String,
    val price: Double,
    val oldPrice: Double?,
    val priceUnit: String?,
    val discount: String,
    val imageUrl: URL,
    val productLink: URL,
    val supermarket: String
)
