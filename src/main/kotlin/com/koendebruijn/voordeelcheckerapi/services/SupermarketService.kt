package com.koendebruijn.voordeelcheckerapi.services

import com.koendebruijn.voordeelcheckerapi.scrapers.AhScraper
import com.koendebruijn.voordeelcheckerapi.scrapers.DirkScraper
import com.koendebruijn.voordeelcheckerapi.scrapers.JumboScraper
import com.koendebruijn.voordeelcheckerapi.scrapers.Supermarket
import org.springframework.stereotype.Service

@Service
class SupermarketService {
    private final val supermarkets = listOf(
        Supermarket(id = 1, name = "Albert Heijn", scraper = AhScraper()),
        Supermarket(id = 2, name = "Jumbo", scraper = JumboScraper()),
        Supermarket(id = 3, name = "Dirk", scraper = DirkScraper())
    )

    fun getSupermarkets(): List<Supermarket> {
        return supermarkets
    }
}