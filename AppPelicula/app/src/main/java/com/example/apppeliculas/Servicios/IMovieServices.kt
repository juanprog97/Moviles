package com.example.apppeliculas.Servicios
import com.example.apppeliculas.Modelos.Movie

interface IMovieServices {

    fun verifyMovie(movie: Movie) : Boolean
    fun consultMovies(): List<Movie>?
    fun saveMovie(movie: Movie)

}