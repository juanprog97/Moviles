package com.example.apppeliculas.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.example.apppeliculas.Adapters.MovieListAdapter
import com.example.apppeliculas.Modelos.Movie
import com.example.apppeliculas.Modelos.User
import com.example.apppeliculas.R
import com.example.apppeliculas.Servicios.DBServices


class listMovieActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var DBServices : DBServices
    private var  userOnline : Int =-1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listmovie)
        this.userOnline = intent.getIntExtra("IdUser",-1)
        println(this.userOnline)
        DBServices= DBServices(this)
        val listPosts: List<Movie>? = DBServices(this).consultMovies(this.userOnline)
        println(listPosts)
        listView = findViewById<ListView>(R.id.listMovie) as ListView
        val adapter = MovieListAdapter(this, listPosts)
        listView.adapter = adapter
        listView.setClickable(true)
        listView.setOnItemClickListener { adapterView, view, i, l ->

            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("nombre",adapter.getName(i))
            intent.putExtra("año",adapter.getYear(i))
            intent.putExtra("sipnosis",adapter.getSinopsis(i))
            intent.putExtra("images",adapter.getImages(i))
            intent.putExtra("reserva", adapter.getReserva(i))
            intent.putExtra("IdUser",this.userOnline)
            intent.putExtra("idPeli", adapter.getIdPelicula(i))
            startActivity(intent)

        }

    }
    fun agregar(view: View){
        val intent = Intent(this, SaveMovieActivity::class.java)
        intent.putExtra("IdUser",this.userOnline)
        startActivity(intent)

    }

    fun salir(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}






