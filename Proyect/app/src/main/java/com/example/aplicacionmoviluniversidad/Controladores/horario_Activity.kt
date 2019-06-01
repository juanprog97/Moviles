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
import com.example.aplicacionmoviluniversidad.Servicios.CursosServices

class horario_Activity : AppCompatActivity() {
    private lateinit var correo : String
    private lateinit var key : String
    private lateinit var token: String
    private lateinit var CursoService : CursosServices
    override fun onCreate(savedInstanceState: Bundle?) {
        this.correo = intent.getStringExtra("email")
        this.key = intent.getStringExtra("key")
        this.token = intent.getStringExtra("token")
        super.onCreate(savedInstanceState)
        CursoService= CursosServices(this)
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

        var data= this.CursoService.Buscar(this.key,this.token)
        var convert = this.CursoService.OrganizarHorario(data)


        //Testeo
        //Este Codigo se debe copiar en la parte principal de del programa//
        //Se agregan los datos que viene en la Api al adaptador

        var expan =findViewById<ExpandableListView>(R.id.HorExp)
        var adapter = horarioDayAdapter(this,dias,convert)
        expan.setAdapter(adapter)
        for(i in 0 until adapter.groupCount){ //Este ciclo hace que se expanda por defecto
            expan.expandGroup(i,true)

        }


    }
}
