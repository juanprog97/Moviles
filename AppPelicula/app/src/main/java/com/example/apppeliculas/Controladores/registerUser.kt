package com.example.apppeliculas.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.apppeliculas.R
import java.time.LocalDateTime

class registerUser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)
    }

    fun volver(view: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)

    }
    fun registro(view: View){
        val nombre = findViewById<EditText>(R.id.nombre)
        val correo = findViewById<EditText>(R.id.correo)
        val contra  = findViewById<EditText>(R.id.pass)
        val  edad =  findViewById<EditText>(R.id.edad)
    }
}
