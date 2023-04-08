package com.example.mynestedrv.repositorty

import com.example.mynestedrv.network.MovieService

class MainRepository(private val movieService: MovieService) {
    suspend fun getAllMovies() = movieService.getAllMovies()
}