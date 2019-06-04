package com.example.aplicacionmoviluniversidad.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.example.aplicacionmoviluniversidad.Adaptadores.listDispensadorAdapter
import com.example.aplicacionmoviluniversidad.Modelos.Dispensador
import com.example.aplicacionmoviluniversidad.Modelos.Nota
import com.example.aplicacionmoviluniversidad.R

class dispensador_Activity : AppCompatActivity() {
    private lateinit var correo : String
    private lateinit var key : String
    private lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        this.correo = intent.getStringExtra("email")
        this.key = intent.getStringExtra("key")
        this.token = intent.getStringExtra("token")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dispensador_)

        val listas = arrayListOf<Dispensador>()
        val lugar1 = Dispensador("Saman",0)
        val lugar2 = Dispensador("Palmas",0)
        listas.add(lugar2)
        listas.add(lugar1)
        var lista = findViewById<ListView>(R.id.dispen)
        var adapter = listDispensadorAdapter(this,listas)
        lista.adapter = adapter
    }

    fun regresar(view: View){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("key", this.key)
        intent.putExtra("token", this.token)
        intent.putExtra("email",this.correo )
        startActivity(intent)

    }
}
