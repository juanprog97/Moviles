package com.example.apppeliculas.Controladores

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.apppeliculas.Modelos.Reserva
import com.example.apppeliculas.R
import com.example.apppeliculas.Servicios.DBServices


class ProfileActivity : AppCompatActivity() {
    private var userOnline: Int = -1
    private var idPeli: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val  nombrePeli: String = intent.getStringExtra("nombre")
        val  sipnosis: String = intent.getStringExtra("sipnosis")
        val  año: String = intent.getStringExtra("año")
        val image: ByteArray =  intent.getByteArrayExtra("images")
        this.userOnline = intent.getIntExtra("IdUser",-1)
        this.idPeli = intent.getIntExtra("idPeli",-1)
        val imate : Bitmap = BitmapFactory.decodeByteArray(image, 0, image.size)
        val nombreId = findViewById<TextView>(R.id.nombrePelicula)
        val añoId = findViewById<TextView>(R.id.añoPelicula)
        val sipnosisId = findViewById<TextView>(R.id.sipnosisPeli)
        val imageprofile = findViewById<ImageView>(R.id.imagenPelicula)
        imageprofile.setImageBitmap(imate);
        val datosReserva = Reserva(null,this.idPeli,this.userOnline)
        println(this.userOnline)
        val reservado_t : Boolean = DBServices(this).consulReserva(datosReserva)
        val boton = findViewById<Button>(R.id.reserva)
        println(reservado_t)
        if(reservado_t == true){
            boton.visibility = View.GONE
        }else{
            boton.visibility = View.VISIBLE
        }

        añoId.text = año
        nombreId.text = nombrePeli
        sipnosisId.text = sipnosis
    }
    fun reservar(view: View){
        val boton = findViewById<Button>(R.id.reserva)
        val datosReserva = Reserva(null,this.idPeli,this.userOnline)
        DBServices(this).saveReserva(datosReserva)

        boton.visibility = View.GONE
        Toast.makeText(this, "Se ha reservado Pelicula", Toast.LENGTH_SHORT)  .show()

    }
    fun volver(view: View)
    {
        val intent = Intent(this, listMovieActivity::class.java)
        intent.putExtra("IdUser",userOnline)
        startActivity(intent)
    }
}
