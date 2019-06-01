package com.example.aplicacionmoviluniversidad.Servicios

import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import com.example.aplicacionmoviluniversidad.Modelos.Curso
import com.example.aplicacionmoviluniversidad.Modelos.Horario
import com.example.aplicacionmoviluniversidad.Modelos.UserModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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

    fun OrganizarHorario(cursos: List<Curso>): List<List<Horario>>{
        var date = Date();
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val answer = formatter.format(date)
        val dateActual: Date = formatter.parse(answer)

        val Horarios = mutableListOf(
            mutableListOf<Horario>(),
            mutableListOf<Horario>(),
            mutableListOf<Horario>(),
            mutableListOf<Horario>(),
            mutableListOf<Horario>(),
            mutableListOf<Horario>(),
            mutableListOf<Horario>())
        for(i in 0 until cursos.size){

            var tmpHorario = cursos[i].horario

            for(j in 0 until tmpHorario.size){
                if(tmpHorario[j].dia != 0){
                    val ini: String = tmpHorario[j].feci!!
                    val fin: String = tmpHorario[j].fecf!!
                    val iniF: Date  = formatter.parse(ini)
                    val finF: Date  = formatter.parse(fin)
                    if(dateActual >= iniF && dateActual<=finF){
                        val horaAdd = Horario(tmpHorario[j].saln,tmpHorario[j].hora,tmpHorario[j].dia-1,cursos[i].nom)
                        println(tmpHorario[j].dia)
                        Horarios[tmpHorario[j].dia-1].add(horaAdd)
                    }
                }
            }
        }
        return Horarios
    }

}