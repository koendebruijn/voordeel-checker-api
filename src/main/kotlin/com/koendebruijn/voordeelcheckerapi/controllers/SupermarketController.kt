package com.koendebruijn.voordeelcheckerapi.controllers

import com.koendebruijn.voordeelcheckerapi.scrapers.Supermarket
import com.koendebruijn.voordeelcheckerapi.services.SupermarketService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/supermarkets")
class SupermarketController(private val supermarketService: SupermarketService) {


    @GetMapping
    fun getSupportedSupermarkets(): List<Supermarket> {
        return supermarketService.getSupermarkets()
    }
}