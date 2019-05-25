package com.example.aplicacionmoviluniversidad.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.ExpandableListView
import android.widget.Toast
import com.example.aplicacionmoviluniversidad.Adaptadores.cursosExpandibleAdapter
import com.example.aplicacionmoviluniversidad.Modelos.Curso
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
        val nota1 = Nota("Parcial 1",30,4.5)
        val nota2 = Nota("Parcial 2",30,4.0)
        val nota3 = Nota("proyecto",40,3.0)
        val listNo = arrayListOf<Nota>()
        listNo.add(nota1)
        listNo.add(nota2)
        listNo.add(nota3)
        val curs = Curso("Desarrollito","32234243","50%","4.5",listNo)
        val lista = arrayListOf<Curso>()
        lista.add(curs)
        var expan =findViewById<ExpandableListView>(R.id.lvExp)
        var adapter = cursosExpandibleAdapter(this,lista)
        expan.setAdapter(adapter)
        expan.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            adapter.getNotas(groupPosition)

            val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_notas, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)

            mDialogView.Materia.text = adapter.getNombre(groupPosition)

            val mAlertDialog = mBuilder.show()
            mDialogView.cerrar.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
            }
            //Toast.makeText(this, "Long click detected"+groupPosition.toString() ,Toast.LENGTH_SHORT).show()
            true
        }
    }

}


