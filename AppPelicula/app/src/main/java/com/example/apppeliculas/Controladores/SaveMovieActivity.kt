package com.example.apppeliculas.Controladores

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast

import com.example.apppeliculas.R
import com.example.apppeliculas.Modelos.Movie
import com.example.apppeliculas.Servicios.BitMapServices

import com.example.apppeliculas.Servicios.SaveMovieServices

class SaveMovieActivity : AppCompatActivity(){
    private lateinit var SaveMovieServices : SaveMovieServices
    private  var image: ByteArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addmovie)
        SaveMovieServices= SaveMovieServices(this)

    }

    fun SaveMovie(view: View){
        println("hola")
        val title = findViewById<EditText>(R.id.nombrePelicula);
        val year = findViewById<EditText>(R.id.año);
        val sinopsis = findViewById<EditText>(R.id.sipnosis);
        if(this.image != null){
            if( TextUtils.isEmpty(title.text.toString())==false && TextUtils.isEmpty(year.text.toString())==false &&  year.text.toString().toIntOrNull()!=null &&TextUtils.isEmpty(sinopsis.text.toString())==false){
                val movie= Movie(null,year.text.toString().toInt(),title.text.toString(),sinopsis.text.toString(),this.image)
                title.text.clear()
                year.text.clear()
                sinopsis.text.clear()
                SaveMovieServices(this).saveMovie(movie)
                Toast.makeText(this, "Se ha Guardado una pelicula", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "La pelicula ya existe, por favor intentelo de nuevo", Toast.LENGTH_SHORT)  .show()
            }
        }
        else{
            Toast.makeText(this, "Falta por subir una imagen", Toast.LENGTH_SHORT)  .show()
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
