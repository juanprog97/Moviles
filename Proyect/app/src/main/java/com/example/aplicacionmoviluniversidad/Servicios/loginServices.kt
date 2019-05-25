package com.example.aplicacionmoviluniversidad.Servicios

import android.content.Context
import com.example.aplicacionmoviluniversidad.Modelos.UserModel
import java.net.HttpURLConnection
import java.net.URL

class LoginServices(context: Context) {

    fun validate(user: UserModel) : String{
        println(user)
        val connection = URL("http://replica.javerianacali.edu.co:8100/WSMobile/mobile/v2/Autenticacion/?usu="+user.user.toString()+"&pass="+user.password).openConnection() as HttpURLConnection
        val data = connection.inputStream.bufferedReader().readText()
        return data
    }
}