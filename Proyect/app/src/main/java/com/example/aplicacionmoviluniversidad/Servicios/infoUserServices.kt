package com.example.aplicacionmoviluniversidad.Servicios

import android.content.Context
import java.net.HttpURLConnection
import java.net.URL

class infoUserServices(context: Context) {

    fun validate(email: String) : String{
        val connection = URL("http://replica.javerianacali.edu.co:8100/WSMobile/mobile/v2/infoUser?email="+email).openConnection() as HttpURLConnection
        val data = connection.inputStream.bufferedReader().readText()
        return data
    }
}