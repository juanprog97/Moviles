package com.example.apppeliculas.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.example.apppeliculas.Adapters.MovieListAdapter
import com.example.apppeliculas.Modelos.Movie
import com.example.apppeliculas.R
import com.example.apppeliculas.Servicios.MovieDBServices


class listMovieActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var MovieDBServices : MovieDBServices
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listmovie)
        MovieDBServices= MovieDBServices(this)
        val listPosts: List<Movie>? = MovieDBServices(this).consultMovies()
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
            startActivity(intent)
            Toast.makeText(this, "Item Clicked " + adapter.getName(i),Toast.LENGTH_SHORT).show()

        }

    }
    fun agregar(view: View){
        val intent = Intent(this, SaveMovieActivity::class.java)
        startActivity(intent)

    }
}
