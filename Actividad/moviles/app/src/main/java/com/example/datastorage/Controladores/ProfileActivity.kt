package com.example.datastorage.Controladores

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.datastorage.R

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val  name: String = intent.getStringExtra("name")
        val  correo: String = intent.getStringExtra("email")
        val  age: String = intent.getStringExtra("age")
        val image: ByteArray =  intent.getByteArrayExtra("images")
        val imate : Bitmap = BitmapFactory.decodeByteArray(image, 0, image.size)
        val nameId = findViewById<TextView>(R.id.nameProfile)
        val ageId = findViewById<TextView>(R.id.ageProfile)
        val correoId = findViewById<TextView>(R.id.emailProfile)
        val imageprofile = findViewById<ImageView>(R.id.imageProfile)
        imageprofile.setImageBitmap(imate);
        ageId.text = age
        nameId.text = name
        correoId.text = correo
    }

    fun Volver(view: View)
    {
        val intent = Intent(this, UsersListActivity::class.java)
        startActivity(intent)
    }
}
