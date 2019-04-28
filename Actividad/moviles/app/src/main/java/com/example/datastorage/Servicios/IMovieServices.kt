package com.example.datastorage.Servicios
import com.example.datastorage.Modelos.Movie

interface IMovieServices {

    fun verifyMovie(movie: Movie) : Boolean
    fun consultMovies(): List<Movie>?
    fun saveMovie(movie: Movie)

}