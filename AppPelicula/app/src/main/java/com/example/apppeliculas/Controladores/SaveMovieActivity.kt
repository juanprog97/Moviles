package com.example.apppeliculas.Controladores

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.apppeliculas.R
import com.example.apppeliculas.Modelos.Movie
import com.example.apppeliculas.Servicios.BitMapServices
import com.example.apppeliculas.Servicios.MovieDBServices
import com.example.apppeliculas.Servicios.SaveMovieServices

class SaveMovieActivity : AppCompatActivity(){

    private lateinit var image: ByteArray
    private lateinit var SaveMovieServices : SaveMovieServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addmovie)
        SaveMovieServices= SaveMovieServices(this)
    }

    fun SaveMovie(view: View){
        println("hola")
        val title = findViewById<EditText>(R.id.nombrePelicula);
        val year = findViewById<EditText>(R.id.año);
        val sinopsis = findViewById<TextView>(R.id.sipnosis);

        if( TextUtils.isEmpty(title.text.toString())==false && TextUtils.isEmpty(year.text.toString())==false &&  year.text.toString().toIntOrNull()!=null &&TextUtils.isEmpty(sinopsis.text.toString())==false){
            val movie= Movie(null,year.text.toString().toInt(),title.text.toString(),sinopsis.text.toString(),this.image)
            SaveMovieServices(this).saveMovie(movie)
        }
    }

    fun cargarImagen(view: View) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.setType("image/");
        startActivityForResult(intent,1)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== RESULT_OK && requestCode== 1 && data!=null){
            val imageUri= data?.data
            val bitmap= MediaStore.Images.Media.getBitmap(this.contentResolver,imageUri)
            this.image= BitMapServices(this).getBytes(bitmap)
        }
    }
}
