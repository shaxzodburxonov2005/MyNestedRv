package com.example.mynestedrv.network

import androidx.annotation.Keep
import com.example.mynestedrv.model.movie.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("/")
    suspend fun getAllMovies(
        @Query("apikey") apiKey: String = "17340be9",
        @Query("s") title: String = "avengers"
    ): Response<Movie>
}