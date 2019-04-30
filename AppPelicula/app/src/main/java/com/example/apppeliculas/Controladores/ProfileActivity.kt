package com.example.apppeliculas.Controladores

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.apppeliculas.R


class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val  nombrePeli: String = intent.getStringExtra("name")
        val  sipnosis: String = intent.getStringExtra("sipnosis")
        val  año: String = intent.getStringExtra("año")
        val image: ByteArray =  intent.getByteArrayExtra("images")
        val imate : Bitmap = BitmapFactory.decodeByteArray(image, 0, image.size)
        val nombreId = findViewById<TextView>(R.id.nombrePelicula)
        val añoId = findViewById<TextView>(R.id.añoPelicula)
        val sipnosisId = findViewById<TextView>(R.id.sipnosisPeli)
        val imageprofile = findViewById<ImageView>(R.id.imagenPelicula)
        imageprofile.setImageBitmap(imate);
        añoId.text = año
        nombreId.text = nombrePeli
        sipnosisId.text = sipnosis
    }
    fun reserva(view: View){
        println("Implementar")
    }
    fun volver(view: View)
    {
        val intent = Intent(this, listMovieActivity::class.java)
        startActivity(intent)
    }
}
