package com.example.aplicacionmoviluniversidad.Servicios

import android.content.Context
import android.os.StrictMode
import com.example.aplicacionmoviluniversidad.Modelos.Curso
import com.example.aplicacionmoviluniversidad.Modelos.Noticia
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.HttpURLConnection
import java.net.URL

class anunciosServices(context: Context) {
    fun BuscarNoticia(llave: String, token: String) : List<Noticia>{
        val SDK_INT = android.os.Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        val connection = URL("http://replica.javerianacali.edu.co:8100/WSMobile/mobile/v2/noticias?page=2&limit=10&filter=").openConnection() as HttpURLConnection
        val data = connection.inputStream.bufferedReader().readText()
        return Gson()?.fromJson(data, object : TypeToken<List<Noticia>>(){}.type)


    }
}