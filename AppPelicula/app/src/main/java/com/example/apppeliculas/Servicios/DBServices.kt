package com.example.apppeliculas.Servicios

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.apppeliculas.Modelos.Movie
import com.example.apppeliculas.Modelos.Reserva
import com.example.apppeliculas.Modelos.User

class DBServices(context: Context) : SQLiteOpenHelper(context, "DBService", null, 1), IUserServices, IMovieServices,IReservaServices
{
    override fun onCreate(db: SQLiteDatabase?) {
        val table1 : String = "CREATE TABLE users(idUser integer  primary key autoincrement ," +
                           " name text," +
                           " email text," +
                           " age integer," +
                           " password text);"
        val table2: String= "CREATE TABLE Movies (idPelicula integer primary key autoincrement ," +
                           " year integer," +
                           " title text," +
                           " sinopsis text," +
                           " images blob);"
        val table3: String = "CREATE TABLE Reserva (idReserva integer primary key autoincrement ,"+
                           " idUser integer ,"+
                           " idPelicula integer ," +
                           " FOREIGN KEY (idUser) REFERENCES users(idUser),"+
                           " FOREIGN KEY (idPelicula) REFERENCES Movies(idPelicula));"

        /*val drop1:String = "DROP TABLE Movies"
        val drop2:String = "DROP TABLE users"
        val drop3:String = "DROP TABLE Reserva"
        db?.execSQL(drop1)
        db?.execSQL(drop2)
        db?.execSQL(drop3)*/
        db?.execSQL(table1)
        db?.execSQL(table2)
        db?.execSQL(table3)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        TODO("Sin implementación")
    }

    override fun verifyUser(user: User) : Boolean
    {
        val sql : String = "SELECT email, password FROM users" +
                           " where email='${user.email}'"

        val result : Cursor = this.executeQuery(sql, this.writableDatabase)
        var returnValue : Boolean = false

        if(result.moveToFirst())
        {
            if (user.email.equals(result.getString(0)) && user.password.equals(result.getString(1)))
            {
                returnValue = true
            }
        }

        this.close()
        return returnValue
    }

    override fun consulReserva(reserva: Reserva):Boolean {
        val consult : String = "SELECT * FROM Reserva" +
                " where idUser=${reserva.usuario} and idPelicula=${reserva.pelicula}"

        val result : Cursor = this.executeQuery(consult, this.writableDatabase)
        var returnValue : Boolean = false

        if(result.moveToFirst())
        {
                returnValue = true
        }

        this.close()
        return returnValue
    }

    override fun consultId(user : User) : Int
    {
        val sql : String = "SELECT idUser ,email, password FROM users" +
                " where email='${user.email}'"

        val result : Cursor = this.executeQuery(sql, this.writableDatabase)
        var returnValue : Int = -1

        if(result.moveToFirst())
        {
            if (user.email.equals(result.getString(1)) && user.password.equals(result.getString(2)))
            {
                returnValue = result.getString(0).toInt()
            }
        }

        this.close()
        return returnValue

    }
    override fun saveUser(user: User)
    {
        var localUser = ContentValues()
        localUser.put("name", user.name)
        localUser.put("email", user.email)
        localUser.put("age", user.age)
        localUser.put("password", user.password)
        this.executeModificationUser(localUser)
    }

    override fun consultUsers(): List<User>?
    {
        val sql : String = "SELECT idUser, name, email, age, password FROM users"
        val result : Cursor = this.executeQuery(sql, this.writableDatabase)
        var listUsers : MutableList<User>? = ArrayList<User>()
        result.moveToFirst()

        while(!result.isAfterLast)
        {

            var user : User = User(
                result.getInt(0),
                result.getString(1),
                result.getString(2),
                result.getInt(3),
                result.getString(4)
            )

            listUsers?.add(user)
            result.moveToNext()
        }

        return listUsers
    }


    private fun executeQuery(sql: String, bd : SQLiteDatabase) : Cursor
    {
        val consulta : Cursor = bd.rawQuery(sql, null)
        return consulta
    }

    private fun executeModificationUser(user: ContentValues)
    {
        val bd = this.writableDatabase
        println("Se guardo")
       bd.insert("users", null , user)
    }

    //-------Seccion Usuarios




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
                returnValue = true
            }
        }

        this.close()
        return returnValue
    }

    override fun saveReserva(reserva: Reserva){
        var localUser = ContentValues()
        localUser.put("idUser", reserva.usuario)
        localUser.put("idPelicula", reserva.pelicula)
        this.executeModificationReserva(localUser)
    }

    private fun executeModificationReserva(reserva: ContentValues)
    {
        val bd = this.writableDatabase
        bd.insert("Reserva", null , reserva)
    }

    override fun consultMovies(user:Int): List<Movie>?
    {
        /*val  sql : String = "SELECT m.idPelicula ,m.title,m.year,"+
                "m.sinopsis,m.images,r.reserva, u.idUser from Movies m  " +
                "inner join Reserva r on m.idPelicula  = r.idPelicula "+
                "inner join users u on u.idUser = r.idUser";*/
        val sql : String = "SELECT idPelicula, title, year, sinopsis ,images FROM Movies"
        val result : Cursor = this.executeQuery(sql, this.writableDatabase)
        var listMovies : MutableList<Movie>? = ArrayList<Movie>()
        result.moveToFirst()

        while(!result.isAfterLast)
        {
            val consult : String = "SELECT * FROM Reserva" +
                    " where idUser=${user} and idPelicula=${result.getInt(0)}"
            val veri : Cursor = this.executeQuery(consult, this.writableDatabase)
            var Movie: Movie
            if(veri.moveToFirst() ) {
                Movie = Movie(
                    result.getInt(0),
                    result.getInt(2),
                    result.getString(1),
                    result.getString(3),
                    ("si"),
                    result.getBlob(4)

                )
            }
            else{
                    Movie = Movie(
                    result.getInt(0),
                    result.getInt(2),
                    result.getString(1),
                    result.getString(3),
                    ("no"),
                    result.getBlob(4)

                )

            }

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
        this.executeModificationMovie(localMovie)
    }

    private fun executeModificationMovie(movie: ContentValues)
    {
        val bd = this.writableDatabase
        // bd.execSQL("INSERT INTO users VALUES (null,user.name,user.email,user.age,user.password,user.images)");
        bd.insert("Movies", null , movie)
    }


    //Seccion de Peliculas
}