package com.koendebruijn.voordeelcheckerapi.services

import com.koendebruijn.voordeelcheckerapi.dto.Product

interface SupermarketScraper {

    fun scrapeWebsite(productName: String): List<Product>
}