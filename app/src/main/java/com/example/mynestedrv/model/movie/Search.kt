package com.example.mynestedrv.model.movie

data class Search(
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
) : java.io.Serializable
