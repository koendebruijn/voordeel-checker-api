package com.koendebruijn.voordeelcheckerapi.services

import com.koendebruijn.voordeelcheckerapi.dto.Product
import org.jsoup.Jsoup
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.net.URL

@Service
class AhScraper : SupermarketScraper {
    private final val baseUrl = "https://www.ah.nl/zoeken"

    @Async
    override fun scrapeWebsite(productName: String): List<Product> {
        val products = mutableListOf<Product>()
        val query = "?query=$productName&kenmerk=bonus"

        val doc = Jsoup.connect(baseUrl + query).get()

        val articles = doc.getElementsByClass("product-card-portrait_root__sZL4I")

        for (article in articles) {
            val nameElement = article.getElementsByTag("strong").first()
            val priceElement = article.getElementsByClass("price-amount_highlight__3WjBM").first()
            val oldPriceElement = article.getElementsByClass("price-amount_was__1PrUY").first()
            val discountElement = article.getElementsByClass("shield_root__388LZ").first()
            val priceUnitElement = article.getElementsByClass("price_unitSize__8gRVX").first()
            val imageSrcElement = article.getElementsByTag("img").first()
            val productLink = article.getElementsByTag("a").first()!!.attributes().get("href")


            products.add(Product(
                name = nameElement!!.text(),
                supermarket = "AH",
                imageUrl = URL(imageSrcElement!!.attributes().get("src")),
                discount = discountElement!!.text(),
                oldPrice = oldPriceElement?.text()?.toDouble(),
                price = priceElement!!.text().toDouble(),
                priceUnit = priceUnitElement!!.text(),
                productLink = URL("https://www.ah.nl$productLink")
            ))
        }


        return products
    }
}