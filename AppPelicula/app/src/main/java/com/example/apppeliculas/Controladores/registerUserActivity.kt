package com.example.apppeliculas.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.apppeliculas.R
import com.example.apppeliculas.Modelos.User
import com.example.apppeliculas.Servicios.DBServices


class registerUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)
    }

    fun volver(view: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun String.isValidEmail(): Boolean
            = this.isNotEmpty() &&
            Patterns.EMAIL_ADDRESS.matcher(this).matches()


    fun registro(view: View){
        val nombre = findViewById<EditText>(R.id.nombre)
        val correo = findViewById<EditText>(R.id.correo)
        val contra  = findViewById<EditText>(R.id.contra)
        val edad =  findViewById<EditText>(R.id.edad)
        if(TextUtils.isEmpty(nombre.text.toString())==false  &&  TextUtils.isEmpty(correo.text.toString())==false   && TextUtils.isEmpty(contra.text.toString())==false  && TextUtils.isEmpty(edad.text.toString())==false )
        {
            val user = User(null, nombre.text.toString(), correo.text.toString(),edad.text.toString().toInt(), contra.text.toString());
            if(user.email!!.isValidEmail())
            {
                if( !DBServices(this).verifyUser(user)){
                    nombre.text.clear()
                    contra.text.clear()
                    correo.text.clear()
                    edad.text.clear()
                    DBServices(this).saveUser(user)
                    Toast.makeText(this, "Se ha Guardado Un Usuario", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "El usuario ya existe, por favor intentelo de nuevo", Toast.LENGTH_SHORT)  .show()
                }

            }
            else{
                Toast.makeText(this, "Correo Mal Escrito", Toast.LENGTH_SHORT)  .show()
            }

            /* val intent = Intent(this, UsersListActivity::class.java)
             startActivity(intent);*/ //
        }else{
            Toast.makeText(this, "Faltan Campos por llenar correctamente",  Toast.LENGTH_SHORT).show()
        }



    }
}





