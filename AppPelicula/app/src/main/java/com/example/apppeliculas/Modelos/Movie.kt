package com.example.apppeliculas.Modelos

data class Movie (
    val idUser: Int?,
    val year: Int?,
    val title: String?,
    val sinopsis: String?,
    var images: ByteArray?
)