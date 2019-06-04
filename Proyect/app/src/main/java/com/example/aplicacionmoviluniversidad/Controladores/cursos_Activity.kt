package com.example.aplicacionmoviluniversidad.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.aplicacionmoviluniversidad.Adaptadores.cursosExpandibleAdapter
//import com.example.aplicacionmoviluniversidad.Adaptadores.listNotasAdapter
import com.example.aplicacionmoviluniversidad.Modelos.Curso
import com.example.aplicacionmoviluniversidad.Modelos.DiaClase
import com.example.aplicacionmoviluniversidad.Modelos.Horario
import com.example.aplicacionmoviluniversidad.Modelos.Nota
import com.example.aplicacionmoviluniversidad.R
import com.example.aplicacionmoviluniversidad.Servicios.CursosServices
import kotlinx.android.synthetic.main.activity_cursos_.*
import kotlinx.android.synthetic.main.dialog_notas.view.*

class cursos_Activity : AppCompatActivity() {
    private lateinit var correo : String
    private lateinit var key : String
    private lateinit var token: String
    private lateinit var CursoService : CursosServices
    private lateinit var listView: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        this.correo = intent.getStringExtra("email")
        this.key = intent.getStringExtra("key")
        this.token = intent.getStringExtra("token")
        super.onCreate(savedInstanceState)
        CursoService= CursosServices(this)
        setContentView(R.layout.activity_cursos_)

        val lista = this.CursoService.Buscar(this.key,this.token)
        println(lista)
        //Testeo

        //Este Codigo se debe copiar en la parte principal de del programa//
        //Se agregan los datos que viene en la Api al adaptador
        var expan =findViewById<ExpandableListView>(R.id.lvExp)
        var adapter = cursosExpandibleAdapter(this,lista)
        expan.setAdapter(adapter)
        expan.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            //var nota = adapter.getNotas(groupPosition)  //Retorno el Arreglo de las notas
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_notas, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)

            mDialogView.Materia.text = adapter.getNombre(groupPosition)
            //val adapter = listNotasAdapter(this, nota)      //Se actualiza el listView Con los Datos de las notas
            //mDialogView.parciales.adapter = adapter
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
        intent.putExtra("key", this.key)
        intent.putExtra("token", this.token)
        intent.putExtra("email",this.correo )
        startActivity(intent)

    }


}


