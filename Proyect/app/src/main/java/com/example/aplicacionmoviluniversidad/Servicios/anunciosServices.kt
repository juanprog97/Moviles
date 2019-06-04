package com.example.aplicacionmoviluniversidad.Servicios

import android.content.Context
import android.os.StrictMode
import com.example.aplicacionmoviluniversidad.Modelos.Anuncio
import com.example.aplicacionmoviluniversidad.Modelos.Information
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.HttpURLConnection
import java.net.URL

class anunciosServices(context: Context) {
    fun desplegarAnuncios() : Information{
        val SDK_INT = android.os.Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        val connection = URL("http://replica.javerianacali.edu.co:8100/WSMobile/mobile/v2/noticias?page=1&limit=50&filter=").openConnection() as HttpURLConnection
        val data = connection.inputStream.bufferedReader().readText()
        return Gson()?.fromJson(data, object : TypeToken<Information>(){}.type)

    }

    fun extraerNoticias(anuncio:List<Anuncio>): List<Anuncio>{
        var lista = mutableListOf<Anuncio>()
        for(j in 0 until anuncio.size){
            if (anuncio[j].type =="Conexión Javeriana - Noticias"){
                lista.add(anuncio[j])
            }
        }

        return lista

    }

    fun extraerEventos(anuncio:List<Anuncio>): List<Anuncio>{
        var lista = mutableListOf<Anuncio>()
        for(j in 0 until anuncio.size){
            if (anuncio[j].type =="Conexión Javeriana - Eventos"){
                lista.add(anuncio[j])
            }
        }

        return lista

    }

}