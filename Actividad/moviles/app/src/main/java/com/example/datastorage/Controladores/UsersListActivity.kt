package com.example.datastorage.Controladores

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import android.widget.Toast
import com.example.datastorage.Adapters.MovieListAdapter
import com.example.datastorage.Modelos.Movie
import com.example.datastorage.Modelos.User
import com.example.datastorage.R
import com.example.datastorage.Servicios.MovieDBServices
import com.example.datastorage.Servicios.UserDBServices


class UsersListActivity : AppCompatActivity()
{
    private lateinit var listView: ListView
    private lateinit var MovieDBServices : MovieDBServices
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)
        MovieDBServices= MovieDBServices(this)
        val listPosts: List<Movie>? = MovieDBServices(this).consultMovies()
        println(listPosts)
        listView = findViewById<ListView>(R.id.listUsers) as ListView
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
