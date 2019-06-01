package com.example.aplicacionmoviluniversidad.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import com.example.aplicacionmoviluniversidad.Adaptadores.horarioDayAdapter
import com.example.aplicacionmoviluniversidad.Modelos.DiaClase
import com.example.aplicacionmoviluniversidad.Modelos.Horario
import com.example.aplicacionmoviluniversidad.R

class horario_Activity : AppCompatActivity() {
    private lateinit var correo : String
    private lateinit var key : String
    private lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        this.correo = intent.getStringExtra("email")
        this.key = intent.getStringExtra("key")
        this.token = intent.getStringExtra("token")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horario_)

    }
    fun regresar(view: View){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("key", this.key)
        intent.putExtra("token", this.token)
        intent.putExtra("email",this.correo )
        startActivity(intent)

    }
    fun Test(view: View){
        //Los Headers
        val dias = mutableListOf( DiaClase(1,"Lunes"),
            DiaClase(2,"Martes"),
            DiaClase(3,"Miercoles"),
            DiaClase(4,"Jueves"),
            DiaClase(5,"Viernes"),
            DiaClase(6,"Sabado") )
        //Este es el Tipo de dato que debe entrar en la App
        val Horarios = mutableListOf(
            mutableListOf<Horario>(
                Horario("Lagos","10:00 - 12:00",1,"Naturales"),
                Horario("Saman","1:00 - 2:00",1,"Sociales"),
                Horario("Saman","1:00 - 8:00",1,"Ambientacion"),
                Horario("Arrow","1:00 - 1:00",1,"Ofimatica"),
                Horario("Planta baja","1:00 - 5:00",1,"Recreo"),
                Horario("Planta alta","6:00 - 5:00",1,"Culto")
            ),
            mutableListOf<Horario>(Horario("CDL","2:00 - 4:00",2,"Gym")),
            mutableListOf<Horario>(Horario("Linux","2:00 - 4:00",3,"Boxeo")),
            mutableListOf<Horario>(Horario("UDX","1:00 - 4:00",4,"Jardineria")),
            mutableListOf<Horario>(Horario("lago","4:00 - 6:00",5,"Cancion")),
            mutableListOf<Horario>())
        //Testeo

        //Este Codigo se debe copiar en la parte principal de del programa//
        //Se agregan los datos que viene en la Api al adaptador

        var expan =findViewById<ExpandableListView>(R.id.HorExp)
        var adapter = horarioDayAdapter(this,dias,Horarios)
        expan.setAdapter(adapter)
        for(i in 0 until adapter.groupCount){ //Este ciclo hace que se expanda por defecto
            expan.expandGroup(i,true)

        }


    }
}
