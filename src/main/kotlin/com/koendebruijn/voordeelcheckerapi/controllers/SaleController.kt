package com.koendebruijn.voordeelcheckerapi.controllers

import com.koendebruijn.voordeelcheckerapi.dto.Product
import com.koendebruijn.voordeelcheckerapi.services.SaleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/sale")
class SaleController(
    private val saleService: SaleService
) {

    @GetMapping("{productName}")
    fun getDiscounts(@PathVariable productName: String, @RequestParam(name = "supermarkets") supermarketIds: Array<Int>): MutableList<Product> {
        return saleService.getProductsFromSupermarkets(supermarketIds, productName)
    }
}