package com.example.aplicacionmoviluniversidad.Servicios

import android.content.Context
import com.example.aplicacionmoviluniversidad.Modelos.UserModel
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class CursosServices(context: Context) {

    fun BuscarCursos(user: UserModel){
        // Se hace la segunda conexion para traer los datos de las asignaturas
        val auth = Base64.getEncoder().encode((llave+":"+user.toke).toByteArray()).toString(Charsets.UTF_8)
        val connection2 = URL("http://replica.javerianacali.edu.co:8100/WSMobile/mobile/v2/asignaturas").openConnection() as HttpURLConnection
        connection2.setRequestProperty("Authorization", "Basic $auth")
        val data2 = connection.inputStream.bufferedReader().readText()

        // Se visualizan las asignaturas del estudiante
        info2.text = data2
    }
}