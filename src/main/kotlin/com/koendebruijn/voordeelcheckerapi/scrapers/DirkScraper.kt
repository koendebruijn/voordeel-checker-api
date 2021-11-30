package com.koendebruijn.voordeelcheckerapi.scrapers

import com.koendebruijn.voordeelcheckerapi.dto.Product
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import java.net.URL

class DirkScraper : SupermarketScraper {
    private val baseUrl = "https://www.dirk.nl/zoeken/producten"

    override fun scrapeWebsite(productName: String): List<Product> {

        val options = FirefoxOptions()
        options.addArguments("--headless")
        val driver = FirefoxDriver(options)

        val products = mutableListOf<Product>()

        try {
            driver.get("$baseUrl/$productName")

            // Accept cookies
            driver.findElement(By.xpath("/html/body/div/div/div/div[7]/div[2]/div[4]/button")).click()

            // Open filters
            driver.findElement(By.xpath("//*[@id=\"productlist\"]/div/section/button")).click()

            // select filter

            val discounts =
                driver.findElement(By.xpath("/html/body/div[1]/div/div/div[5]/div/div[3]/div/aside/section[2]/ul/li"))
            discounts.click()

            val articles = driver.findElements(By.className("product-card"))

            articles.forEach {
                val name = it.findElement(By.className("product-card__name")).text
                val discount = it.findElement(By.className("product-card__discount")).text
                val priceUnit = it.findElement(By.className("product-card__description")).text
                val price = it.findElement(By.className("product-card__price__new")).text
                val imageSrc = it.findElement(By.tagName("img")).getAttribute("src")
                val productLink = it.findElement(By.className("product-card__image")).getAttribute("href")


                products.add(
                    Product(
                        name = name,
                        discount = discount.replace("\n", " "),
                        priceUnit = priceUnit,
                        price = price.replace(" ", "").toDouble(),
                        imageUrl = URL(imageSrc),
                        productLink = URL("https://www.dirk.nl$productLink"),
                        supermarket = "Dirk"
                    )
                )

            }
        } catch (e: NoSuchElementException) {
            return products
        } catch (e: Exception) {
            throw e
        } finally {
            driver.quit()
        }

        return products
    }
}