package com.plcoding.stockmarketapp.data.csv

import com.opencsv.CSVReader
import com.plcoding.stockmarketapp.domain.model.MovieListing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieListingsParser @Inject constructor() : CSVParser<MovieListing> {

    override suspend fun parse(stream: InputStream): List<MovieListing> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val movie_id = line.getOrNull(0)
                    val overview = line.getOrNull(1)
                    val release_date = line.getOrNull(2)
                    val title = line.getOrNull(3)

                    MovieListing(
                        movie_id = 23,
                        overview = overview ?: return@mapNotNull null,
                        release_date = release_date ?: return@mapNotNull null,
                        title = title ?: return@mapNotNull null
                    )
                }
                .also {
                    csvReader.close()
                }
        }
    }
}