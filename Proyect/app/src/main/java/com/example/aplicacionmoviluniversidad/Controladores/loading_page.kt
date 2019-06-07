package com.example.aplicacionmoviluniversidad.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.aplicacionmoviluniversidad.R
import com.example.aplicacionmoviluniversidad.Servicios.UserDBService

class loading_page : AppCompatActivity() {
    private var dbConexion: UserDBService = UserDBService(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_page)
        if( this.dbConexion.CountUser() ==0){
            Handler().postDelayed({
                /* Create an Intent that will start the Menu-Activity. */
                val intent = Intent(this, login_Activity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                finish()
            }, 5000)

        }else{
            Handler().postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                finish()
            },5000)
        }
    }


}
