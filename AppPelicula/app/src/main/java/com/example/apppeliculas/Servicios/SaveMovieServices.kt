package com.example.apppeliculas.Servicios

import android.content.Context
import com.example.apppeliculas.Modelos.Movie

class SaveMovieServices(context: Context)
{

    private var dbConnection : MovieDBServices = MovieDBServices(context)
    private lateinit var movie : Movie
    //private var sharedConnection : ListMoviesServices = ListMoviesServices(context)

    fun verifyMovie(movie: Movie) : Boolean
    {
        this.movie = movie
        var result : Boolean = dbConnection.verifyMovie(this.movie)

        return result
    }

    fun saveMovie(movie: Movie) {
        println("afuera")
        if( !this.verifyMovie(movie)){
            println("entro")
            dbConnection.saveMovie(movie)
        }
    }
}