package com.koendebruijn.voordeelcheckerapi.services

import com.koendebruijn.voordeelcheckerapi.dto.Product
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service
import java.net.URL

@Service
class JumboScraper : SupermarketScraper {
    private final val pageSize = 24
    private final val baseUrl = "https://www.jumbo.com/producten/"

    override fun scrapeWebsite(productName: String): List<Product> {
        val products = mutableListOf<Product>()
        var hasProducts = true
        var offset = 0
        val allArticles = mutableListOf<Element>()

        while (hasProducts) {
            val query = "?offSet=$offset&searchTerms=$productName&pageSize=$pageSize"

            val doc = Jsoup.connect(baseUrl + query)
                .userAgent("Mozilla/5.0")
                .get()

            val hasNoResults = doc.getElementsByClass("google-zero-result").first()

            if (hasNoResults != null) {
                hasProducts = false
                continue
            }

            allArticles.addAll(doc.getElementsByTag("article"))
            offset += pageSize

        }

        println("fetched all products")

        allArticles.parallelStream()
            .forEach {
                val promotionElement = it.getElementsByClass("promotions").first()

                if (promotionElement == null || promotionElement.text() == "Niet bestelbaar") {
                    return@forEach
                }
                val nameElement = it.getElementsByClass("title-link").first()
                val promotionTypeElement = it.getElementsByClass("jum-tag prominent").first()

                if (promotionTypeElement != null) {
                    val discount = promotionElement.text().trim()
                    val imageElement = it.getElementsByClass("image").first()
                    val priceElement = it.getElementsByClass("current-price").first()
                    val linkElement = it.getElementsByClass("link").first()

                    products.add(
                        Product(
                            name = nameElement!!.text(),
                            price = priceElement!!.text().trim().replace(" ", ".").toDouble(),
                            supermarket = "Jumbo",
                            discount = discount,
                            imageUrl = URL(imageElement?.attributes()?.get("src")),
                            productLink = URL("https://www.jumbo.com" + linkElement!!.attributes().get("href")),
                        )
                    )
                }
            }

        return products

    }
}
