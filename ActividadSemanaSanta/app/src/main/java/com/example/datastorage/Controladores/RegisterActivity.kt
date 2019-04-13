package com.example.datastorage.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.datastorage.Modelos.User
import com.example.datastorage.R
import com.example.datastorage.Servicios.UserDBServices

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun profile(view: View)
    {
        val nombre = findViewById<TextView>(R.id.nombre);
        val correo = findViewById<TextView>(R.id.correo);
        val contra = findViewById<TextView>(R.id.contraseña);
        val edad = findViewById<TextView>(R.id.edad);
        val user = User(null, nombre.text.toString(), correo.text.toString(), edad.text.toString().toInt(), contra.text.toString());
        if(nombre != null &&  correo != null && contra!= null && edad != null && edad != null )
        {
            UserDBServices(this).saveUser(user);
            nombre.text = ""
            correo.text = ""
            contra.text = ""
            edad.text = ""
            Toast.makeText(this, "Se ha Guardado Un Usuario",  Toast.LENGTH_SHORT).show()
           /* val intent = Intent(this, UsersListActivity::class.java)
            startActivity(intent);*/ //
        }else{
            Toast.makeText(this, "Faltan Campos por llenar",  Toast.LENGTH_SHORT).show()
        }

    }
    //juancard190@gmail.com   123456
    //juanmiok@hotmail.es     2468
    fun volver(view:View)
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent);
    }

}

