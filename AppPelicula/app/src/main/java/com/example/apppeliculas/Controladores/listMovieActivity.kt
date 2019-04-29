package com.example.apppeliculas.Controladores

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
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
        /*listView.setClickable(true)
        istView.setOnItemClickListener { adapterView, view, i, l ->

            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("name",adapter.getName(i))
            intent.putExtra("email",adapter.getEmail(i))
            intent.putExtra("age",adapter.getAge(i))
            intent.putExtra("images",adapter.getImages(i))
            startActivity(intent)
            Toast.makeText(this, "Item Clicked " + adapter.getName(i),Toast.LENGTH_SHORT).show()

        }*/

    }
}
