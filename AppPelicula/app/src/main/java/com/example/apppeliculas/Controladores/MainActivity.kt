package com.example.apppeliculas.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.apppeliculas.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun logear(view: View){
        val email = findViewById<EditText>(R.id.correo)
        val pass = findViewById<EditText>(R.id.pass)
    }
    fun register(view: View)
    {
        val intent = Intent(this, registerUserActivity::class.java)
        startActivity(intent)
    }
}

