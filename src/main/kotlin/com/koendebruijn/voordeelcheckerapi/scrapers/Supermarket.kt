package com.koendebruijn.voordeelcheckerapi.scrapers

import com.fasterxml.jackson.annotation.JsonIgnore

data class Supermarket(
    val id: Int,
    val name: String,
    @JsonIgnore
    val scraper: SupermarketScraper)