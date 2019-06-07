package com.example.aplicacionmoviluniversidad.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.aplicacionmoviluniversidad.Adaptadores.prestamosAdapter
import com.example.aplicacionmoviluniversidad.Modelos.LibroPrestamo
import com.example.aplicacionmoviluniversidad.R
import com.example.aplicacionmoviluniversidad.Servicios.CursosServices
import com.example.aplicacionmoviluniversidad.Servicios.libroPrestamoServices

class prestamos_Activity : AppCompatActivity() {
    private lateinit var correo : String
    private lateinit var key : String
    private lateinit var token: String
    private lateinit var libroService: libroPrestamoServices
    override fun onCreate(savedInstanceState: Bundle?) {
        this.correo = intent.getStringExtra("email")
        this.key = intent.getStringExtra("key")
        this.token = intent.getStringExtra("token")
        super.onCreate(savedInstanceState)
        libroService = libroPrestamoServices(this)
        setContentView(R.layout.activity_prestamos_)

        val listaTest = this.libroService.Search(this.key,this.token)
        println(listaTest)
        println(libroService.verifyInputList(listaTest))
        var lista = findViewById<ListView>(R.id.prestamos)
        var adapter = prestamosAdapter(this,listaTest)
        lista.adapter = adapter

    }

    fun regresar(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
