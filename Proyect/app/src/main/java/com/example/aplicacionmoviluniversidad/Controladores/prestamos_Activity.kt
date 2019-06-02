package com.example.aplicacionmoviluniversidad.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.aplicacionmoviluniversidad.Adaptadores.prestamosAdapter
import com.example.aplicacionmoviluniversidad.Modelos.LibroPrestamo
import com.example.aplicacionmoviluniversidad.R

class prestamos_Activity : AppCompatActivity() {
    private lateinit var correo : String
    private lateinit var key : String
    private lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        this.correo = intent.getStringExtra("email")
        this.key = intent.getStringExtra("key")
        this.token = intent.getStringExtra("token")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prestamos_)
    }

    fun regresar(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("key", this.key)
        intent.putExtra("token", this.token)
        intent.putExtra("email", this.correo)
        startActivity(intent)
    }

    fun Test(view: View){
        var listaPrestamos = arrayListOf<LibroPrestamo>(
            LibroPrestamo("sdasd","El Amor en los tiempos del Colera","ODFSDSV V3","Junio 2","junio3",0,"Biblioteca"),
            LibroPrestamo("sdasd","Don Quijote","ODFSDSV V3","Junio 2","junio3",0,"Biblioteca"),
            LibroPrestamo("sdasd","Don Quijote","ODFSDSV V3","Junio 2","junio3",0,"Biblioteca"),
            LibroPrestamo("sdasd","Cien Años de Soledad","ODFSDSV V3","Junio 2","junio3",0,"Biblioteca"),
            LibroPrestamo("sdasd","John Wick","ODFSDSV V3","Junio 2","junio3",0,"Biblioteca"))

        //Si no tiene prestamos se deja un list con un solo elemento sin nada///
       // var listaNull = arrayListOf<LibroPrestamo>(LibroPrestamo("","","","","",0,""))
        var lista = findViewById<ListView>(R.id.prestamos)
        var adapter = prestamosAdapter(this,listaPrestamos)
        lista.adapter = adapter
    }
}
