package com.example.aplicacionmoviluniversidad.Servicios

import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import com.example.aplicacionmoviluniversidad.Modelos.Curso
import com.example.aplicacionmoviluniversidad.Modelos.UserModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class CursosServices(context: Context) {



    fun Buscar(llave: String, token: String) : List<Curso>{
        val SDK_INT = android.os.Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        // Se hace la segunda conexion para traer los datos de las asignaturas
        val connection2 = URL("http://replica.javerianacali.edu.co:8100/WSMobile/mobile/v2/asignaturas").openConnection() as HttpURLConnection
        connection2.setRequestProperty(llave,token)
        val data2 = connection2.inputStream.bufferedReader().readText()
        return Gson()?.fromJson(data2, object : TypeToken<List<Curso>>(){}.type)
    }

}