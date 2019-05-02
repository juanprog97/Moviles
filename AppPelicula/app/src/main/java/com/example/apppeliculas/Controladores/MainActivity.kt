package com.example.apppeliculas.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

import android.widget.Toast
import com.example.apppeliculas.Modelos.User
import com.example.apppeliculas.R
import com.example.apppeliculas.Servicios.LoginServices

class MainActivity : AppCompatActivity() {
    private lateinit var loginServices : LoginServices
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginServices= LoginServices(this)
    }
    fun logear(view: View){
        val email = findViewById<EditText>(R.id.correo)
        val pass = findViewById<EditText>(R.id.pass)
        val user = User(null, null, email.text.toString(), null,pass.text.toString());

        if(this.loginServices.verifyUser(user))
        {
            val intent = Intent(this, listMovieActivity::class.java)
            var idUser: Int = this.loginServices.consultId(user)
            println(idUser)
            intent.putExtra("IdUser",idUser)
            startActivity(intent)
        }
        else
        {
            Toast.makeText(this, "Datos incorrectos",  Toast.LENGTH_SHORT).show()
        }
    }
    fun register(view: View)
    {
        val intent = Intent(this, registerUserActivity::class.java)
        startActivity(intent)
    }


}


