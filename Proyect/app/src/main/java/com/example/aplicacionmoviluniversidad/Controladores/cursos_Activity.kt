package com.example.aplicacionmoviluniversidad.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AlertDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.aplicacionmoviluniversidad.Adaptadores.cursosExpandibleAdapter
import com.example.aplicacionmoviluniversidad.Adaptadores.listNotasAdapter
//import com.example.aplicacionmoviluniversidad.Adaptadores.listNotasAdapter
import com.example.aplicacionmoviluniversidad.Modelos.Curso
import com.example.aplicacionmoviluniversidad.Modelos.DiaClase
import com.example.aplicacionmoviluniversidad.Modelos.Horario
import com.example.aplicacionmoviluniversidad.Modelos.Nota
import com.example.aplicacionmoviluniversidad.R
import com.example.aplicacionmoviluniversidad.Servicios.CursosServices
import com.example.aplicacionmoviluniversidad.Servicios.UserDBService
import kotlinx.android.synthetic.main.activity_cursos_.*
import kotlinx.android.synthetic.main.dialog_notas.view.*
import kotlinx.android.synthetic.main.item_nota.*
import java.text.ChoiceFormat.nextDouble
import kotlin.random.Random.Default.nextInt

class cursos_Activity : AppCompatActivity() {
    private lateinit var listView: LinearLayout
    private var dbConexion: UserDBService = UserDBService(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cursos_)

        val lista = this.dbConexion.consultCurso()
        println(lista)
        //Testeo
        val notas = mutableListOf<Nota>()

        var nombreNota = mutableListOf<String>("Parcial 1", "Parcial 2", "Parcial 3", "Proyecto" )
        var porcentNota = mutableListOf<Int>(20,30, 25,25 )
        val nota1 = Nota(nombreNota[0],3.0,porcentNota[0])
        val nota2 = Nota(nombreNota[1],4.0,porcentNota[1])
        val nota3 = Nota(nombreNota[2],5.0,porcentNota[2])
        val nota4 = Nota(nombreNota[3],4.7,porcentNota[3])
        notas.add(nota1)
        notas.add(nota2)
        notas.add(nota3)
        notas.add(nota4)


        //Este Codigo se debe copiar en la parte principal de del programa//
        //Se agregan los datos que viene en la Api al adaptador
        var expan =findViewById<ExpandableListView>(R.id.lvExp)
        var adapter = cursosExpandibleAdapter(this,lista!!)
        expan.setAdapter(adapter)
        expan.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_notas, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)

            mDialogView.Materia.text = adapter.getNombre(groupPosition)
            val adapter = listNotasAdapter(this, notas)      //Se actualiza el listView Con los Datos de las nota

            mDialogView.parciales.adapter = adapter
            val mAlertDialog = mBuilder.show()
            mDialogView.cerrar.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
            }
            true
        }

    }

    fun regresar(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }


}


