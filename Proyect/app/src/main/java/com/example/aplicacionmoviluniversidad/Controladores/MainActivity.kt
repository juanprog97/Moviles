package com.example.aplicacionmoviluniversidad.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.TextureView
import android.view.View
import android.widget.TextView
import com.example.aplicacionmoviluniversidad.R
import com.example.aplicacionmoviluniversidad.Servicios.infoUserServices
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var InfoUserServices: infoUserServices
    private lateinit var correo : String
    private lateinit var key : String
    private lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        InfoUserServices = infoUserServices(this)
        this.correo = intent.getStringExtra("email")
        this.key = intent.getStringExtra("key")
        this.token = intent.getStringExtra("token")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_principal)
        val data  =this.InfoUserServices.validate(correo)
        val tmp = JSONObject(data)
        val datosUser = tmp.getJSONObject("data")
        var textUser = findViewById<TextView>(R.id.nombrePerfil)
        textUser.text = datosUser.getString("apellido")+", "+datosUser.getString("nombre")


    }
    fun irCursos(view: View){
        val intent = Intent(this, cursos_Activity::class.java)
        intent.putExtra("key", this.key)
        intent.putExtra("token", this.token)
        intent.putExtra("email",this.correo )
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)

    }

    fun irHorario(view:View){
        val intent = Intent(this, horario_Activity::class.java)
        intent.putExtra("key", this.key)
        intent.putExtra("token", this.token)
        intent.putExtra("email",this.correo )
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }

    fun irConfiguracion(view:View){
        val intent = Intent(this, configuracion_Activity::class.java)
        intent.putExtra("key", this.key)
        intent.putExtra("token", this.token)
        intent.putExtra("email",this.correo )
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }

    fun irDispensador(view:View){
        val intent = Intent(this, dispensador_Activity::class.java)
        intent.putExtra("key", this.key)
        intent.putExtra("token", this.token)
        intent.putExtra("email",this.correo )
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }
    fun irPrestamos(view:View){
        val intent = Intent(this, prestamos_Activity::class.java)
        intent.putExtra("key", this.key)
        intent.putExtra("token", this.token)
        intent.putExtra("email",this.correo )
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }

    fun irAnunciones(view:View){
        val intent = Intent(this, anunciones_Activity::class.java)
        intent.putExtra("key", this.key)
        intent.putExtra("token", this.token)
        intent.putExtra("email",this.correo )
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }
}
