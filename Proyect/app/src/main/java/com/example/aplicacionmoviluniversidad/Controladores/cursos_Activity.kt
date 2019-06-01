package com.example.aplicacionmoviluniversidad.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.aplicacionmoviluniversidad.Adaptadores.cursosExpandibleAdapter
import com.example.aplicacionmoviluniversidad.Adaptadores.listNotasAdapter
import com.example.aplicacionmoviluniversidad.Modelos.Curso
import com.example.aplicacionmoviluniversidad.Modelos.DiaClase
import com.example.aplicacionmoviluniversidad.Modelos.Horario
import com.example.aplicacionmoviluniversidad.Modelos.Nota
import com.example.aplicacionmoviluniversidad.R
import kotlinx.android.synthetic.main.activity_cursos_.*
import kotlinx.android.synthetic.main.dialog_notas.view.*

class cursos_Activity : AppCompatActivity() {
    private lateinit var correo : String
    private lateinit var key : String
    private lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        this.correo = intent.getStringExtra("email")
        this.key = intent.getStringExtra("key")
        this.token = intent.getStringExtra("token")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cursos_)

    }

    fun regresar(view: View){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("key", this.key)
        intent.putExtra("token", this.token)
        intent.putExtra("email",this.correo )
        startActivity(intent)

    }

    fun Test(view: View){
        val nota1 = Nota("Parcial 1",20,4.0)
        val nota2 = Nota("Parcial 2",60,3.4)
        val nota3 = Nota("Proyecto",20,2.2)

        val nota5 = Nota("Proyecto",20,2.2)
        val nota6 = Nota("Pr",60,1.2)
        val nota7 = Nota("Pecto",10,0.2)

        val listNo = arrayListOf<Nota>()
        val listNo2 = arrayListOf<Nota>()
        listNo.add(nota1)
        listNo.add(nota2)
        listNo.add(nota3)

        listNo2.add(nota5)
        listNo2.add(nota6)
        listNo2.add(nota7)


        val curs = Curso("Desarrollito","32234243","50%","4.5",listNo)
        val curs2 = Curso("HOLA","23423423","30%","4.5",listNo2)
        val lista = arrayListOf<Curso>()
        lista.add(curs)
        lista.add(curs2)
        //Testeo

        //Este Codigo se debe copiar en la parte principal de del programa//
        //Se agregan los datos que viene en la Api al adaptador
        var expan =findViewById<ExpandableListView>(R.id.lvExp)
        var adapter = cursosExpandibleAdapter(this,lista)
        expan.setAdapter(adapter)
        expan.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            var nota = adapter.getNotas(groupPosition)  //Retorno el Arreglo de las notas
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_notas, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)

            mDialogView.Materia.text = adapter.getNombre(groupPosition)
            val adapter = listNotasAdapter(this, nota)      //Se actualiza el listView Con los Datos de las notas
            mDialogView.parciales.adapter = adapter
            val mAlertDialog = mBuilder.show()
            mDialogView.cerrar.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
            }
            true
        }
        //Codigo---------------------------------
    }

}


