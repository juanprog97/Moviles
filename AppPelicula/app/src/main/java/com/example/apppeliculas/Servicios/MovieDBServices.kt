package com.example.apppeliculas.Servicios

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.apppeliculas.Modelos.Movie


class MovieDBServices(context: Context) : SQLiteOpenHelper(context, "MovieDBServices", null, 1), IMovieServices{

    override fun onCreate(db: SQLiteDatabase?) {

        //val sql: String= "drop table Movies"
        //db?.execSQL(sql)
        val create: String= "Create Table Movies (idUser integer primary key autoincrement ," +
                " year integer," +
                " title text," +
                " sinopsis text," +
                " reserva integer,"+
                " images blob);"




        val sql: String = "CREATE TABLE Reserva (idUser int primary key ,"+
                " idPelicula integer primarykey," +
                " FOREIGN KEY (idUser) REFERENCES users(idUser),"+
                " FOREIGN KEY (idPelicula) REFERENCES Movies(idUser));"
        db?.execSQL(create)
        db?.execSQL(sql)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        TODO("Sin implementación")
    }

    override fun verifyMovie(movie: Movie) : Boolean
    {
        val sql : String = "SELECT title, year FROM Movies" +
                " where title='${movie.title}'"

        val result : Cursor = this.executeQuery(sql, this.writableDatabase)
        var returnValue : Boolean = false

        if(result.moveToFirst())
        {
            if (movie.title.equals(result.getString(0)) )
            {
                print("mo ms");
                returnValue = true
            }
        }

        this.close()
        return returnValue
    }

    private fun executeQuery(sql: String, bd : SQLiteDatabase) : Cursor
    {
        val consulta : Cursor = bd.rawQuery(sql,null)
        return consulta
    }

    override fun consultMovies(): List<Movie>?
    {
       /* val  sql : String = "SELECT m.idUser,m.title,m.year,"+
                            "m.sinopsis,m.images,r.reserva from Reserva r " +
                            "inner join Movies m on m.idUser = r.idPelicula"+
                                    "inner join users u on u.idUser = r.idUser";*/
        val sql : String = "SELECT idUser, title, year, sinopsis, reserva ,images FROM Movies"
        val result : Cursor = this.executeQuery(sql, this.writableDatabase)
        var listMovies : MutableList<Movie>? = ArrayList<Movie>()
        result.moveToFirst()

        while(!result.isAfterLast)
        {

            var Movie : Movie = Movie(
                result.getInt(0),
                result.getInt(2),
                result.getString(1),
                result.getString(3),
                result.getInt(4),
                result.getBlob(5)

            )

            listMovies?.add(Movie)
            result.moveToNext()
        }

        return listMovies
    }


    override  fun saveMovie(movie: Movie)
    {
        println("entro a guardar")
        var localMovie = ContentValues()
        localMovie.put("title", movie.title)
        localMovie.put("year", movie.year)
        localMovie.put("sinopsis", movie.sinopsis)
        localMovie.put("images", movie.images)

        println("entro a guardar")
        this.executeModification(localMovie)
    }

    private fun executeModification(movie: ContentValues)
    {
        val bd = this.writableDatabase
        // bd.execSQL("INSERT INTO users VALUES (null,user.name,user.email,user.age,user.password,user.images)");
        bd.insert("Movies", null , movie)
    }
}
