package com.example.mynestedrv.model.movie

data class Movie(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)