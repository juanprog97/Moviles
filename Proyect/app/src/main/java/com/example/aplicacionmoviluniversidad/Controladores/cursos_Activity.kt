package com.example.aplicacionmoviluniversidad.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import com.example.aplicacionmoviluniversidad.Adaptadores.cursosExpandibleAdapter
import com.example.aplicacionmoviluniversidad.Modelos.Curso
import com.example.aplicacionmoviluniversidad.R
import kotlinx.android.synthetic.main.activity_cursos_.*

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
        val curs = Curso("Desarrollito","32234243","50%","4.5")
        val lista = arrayListOf<Curso>()
        lista.add(curs)
        var expan =findViewById<ExpandableListView>(R.id.lvExp)
        expan.setAdapter(cursosExpandibleAdapter(this,lista))
    }

}
