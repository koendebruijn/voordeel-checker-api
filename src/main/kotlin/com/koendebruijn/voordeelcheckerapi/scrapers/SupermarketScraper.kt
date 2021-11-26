package com.koendebruijn.voordeelcheckerapi.scrapers

import com.koendebruijn.voordeelcheckerapi.dto.Product

interface SupermarketScraper {

    fun scrapeWebsite(productName: String): List<Product>
}