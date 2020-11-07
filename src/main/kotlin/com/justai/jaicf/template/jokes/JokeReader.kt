package com.justai.jaicf.template.jokes

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

object JokeReader {
    private val file: File = File("src/main/resources/shortjokes.csv")
    private val rows: List<List<String>> = csvReader().readAll(file)

    fun randomJoke() : String
    {
        return rows.random()[1];
    }
}