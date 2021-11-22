package com.koendebruijn.voordeelcheckerapi.controllers

import com.koendebruijn.voordeelcheckerapi.dto.Product
import com.koendebruijn.voordeelcheckerapi.services.AhScraper
import com.koendebruijn.voordeelcheckerapi.services.JumboScraper
import com.koendebruijn.voordeelcheckerapi.services.SupermarketScraper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/discounts")
class DiscountController(
    val jumboScraper: JumboScraper,
    val ahScraper: AhScraper

) {

    @GetMapping("{productName}")
    fun getDiscounts(@PathVariable productName: String): List<Product> {
        return jumboScraper.scrapeWebsite(productName) + ahScraper.scrapeWebsite(productName)
    }
}