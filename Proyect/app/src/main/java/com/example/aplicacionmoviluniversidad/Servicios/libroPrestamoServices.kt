package com.example.aplicacionmoviluniversidad.Servicios

import android.content.Context
import android.os.StrictMode
import android.util.Log
import com.example.aplicacionmoviluniversidad.Modelos.Curso
import com.example.aplicacionmoviluniversidad.Modelos.LibroPrestamo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.LineNumberReader
import java.net.HttpURLConnection
import java.net.URL

class libroPrestamoServices (context: Context){

    fun Search(key: String, token: String) : List<LibroPrestamo>{
        val SDK_INT = android.os.Build.VERSION.SDK_INT

        if (SDK_INT > 8 ){
            val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        val LibConection  = URL("http://replica.javerianacali.edu.co:8100/WSMobile/mobile/v2/libros").openConnection() as HttpURLConnection
        LibConection.setRequestProperty(key,token)
        val data2 = LibConection.inputStream.bufferedReader().readText()
        return Gson()?.fromJson(data2, object : TypeToken<List<LibroPrestamo>>(){}.type)
    }

    fun verifyInputList(inputList: List<LibroPrestamo>): List<LibroPrestamo> {
        var lSize = inputList.size;
        val verified : MutableList<LibroPrestamo> = ArrayList()
        if (lSize == 1){
            println("Tiene un solo libro o un saldo en mult")

            if(inputList.get(0).codb == null){
                //Case when only exists a penalty or not have borrowed books.
                verified.add(0, LibroPrestamo(" "," "," "," "," ",inputList.get(0).mult," "))
            }

            else{
                verified.add(0, LibroPrestamo(
                    inputList.get(0).codb,
                    inputList.get(0).titu,
                    inputList.get(0).stopo,
                    inputList.get(0).fecp,
                    inputList.get(0).fecd,
                    inputList.get(0).mult,
                    inputList.get(0).loca))
            }
            return verified
        }
        else{
            return inputList
        }

    }

}