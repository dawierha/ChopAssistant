package com.justai.jaicf.template.products

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

object ProductSearch {
    private val file: File = File("src/main/resources/products.csv")
    private val rows: List<List<String>> = csvReader().readAll(file)
    private val products: MutableMap<String, String> = mutableMapOf()

    init {
        rows.forEach { r -> products.putIfAbsent(r[0], r[1]) }
    }

    fun findProductSection(name: String): String
    {
        return products.getOrDefault(name, "Unknown")
    }
}
