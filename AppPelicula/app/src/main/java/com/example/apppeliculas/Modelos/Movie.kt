package com.example.apppeliculas.Modelos

data class Movie(
    val idPeli: Int?,
    val year: Int?,
    val title: String?,
    val sinopsis: String?,
    val reserva: String?,
    val images: ByteArray?

)