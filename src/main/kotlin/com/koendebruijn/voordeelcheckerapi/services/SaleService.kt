package com.koendebruijn.voordeelcheckerapi.services

import com.koendebruijn.voordeelcheckerapi.dto.Product
import org.springframework.stereotype.Service

@Service
class SaleService(private val supermarketService: SupermarketService) {

    fun getProductsFromSupermarkets(supermarketIds: Array<Int>, productName: String): MutableList<Product> {
        val products = mutableListOf<Product>()
        val supermarkets = supermarketService.getSupermarkets()

        supermarkets.parallelStream().forEach {
            if (it.id in supermarketIds) {
                products += it.scraper.scrapeWebsite(productName)
            }
        }

        return products
    }
}