package com.example.aplicacionmoviluniversidad.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.TextureView
import android.view.View
import android.widget.TextView
import com.example.aplicacionmoviluniversidad.Modelos.UserModel
import com.example.aplicacionmoviluniversidad.R
import com.example.aplicacionmoviluniversidad.Servicios.CursosServices
import com.example.aplicacionmoviluniversidad.Servicios.UserDBService
import com.example.aplicacionmoviluniversidad.Servicios.infoUserServices
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var InfoUserServices: infoUserServices
    private lateinit var cursosServices: CursosServices
    private lateinit var correo : String
    private lateinit var key : String
    private lateinit var token: String
    private var dbConexion: UserDBService = UserDBService(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_principal)
        if(this.dbConexion.CountUser() ==0){
            InfoUserServices = infoUserServices(this)
            cursosServices = CursosServices(this)
            this.correo = intent.getStringExtra("email")
            this.key = intent.getStringExtra("key")
            this.token = intent.getStringExtra("token")


            val data  =this.InfoUserServices.validate(correo)
            val tmp = JSONObject(data)
            val datosUser = tmp.getJSONObject("data")
            var textUser = findViewById<TextView>(R.id.nombrePerfil)
            textUser.text = datosUser.getString("apellido")+", "+datosUser.getString("nombre")
            val user = UserModel(datosUser.getString("nombre"),
                                  null,
                                    null,
                                 datosUser.getString("apellido"),
                                 intent.getStringExtra("email"),
                                 intent.getStringExtra("periodo"),
                                 intent.getStringExtra("emplid") ,
                        "x-t6519fdd1s5q",
                                 intent.getStringExtra("token")
                )
            val dataCursos = this.cursosServices.Buscar(user.nametoken!!,user.value!!)

            this.dbConexion.saveUser(user)
            this.dbConexion.saveCurso(dataCursos)
            //Al entrar por primera vez desde el login se guarda el usuario a la base de datos y se llama
            //A llenar los datos en la base de datos de los cursos y horarios
        }

        else{
            val userTmp = this.dbConexion.GetCurrentUser()
            var textUser = findViewById<TextView>(R.id.nombrePerfil)
            textUser.text = userTmp.apellido +", "+ userTmp.nombre
        }
    }
    fun irCursos(view: View){
        val intent = Intent(this, cursos_Activity::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)

    }

    fun irHorario(view:View){
        val user = this.dbConexion.GetCurrentUser()
        val intent = Intent(this, horario_Activity::class.java)
        intent.putExtra("key", user.nametoken)
        intent.putExtra("token", user.value)
        intent.putExtra("email", user.email )
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }

    fun irConfiguracion(view:View){
        val intent = Intent(this, configuracion_Activity::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }

    fun irDispensador(view:View){
        val user = this.dbConexion.GetCurrentUser()
        val intent = Intent(this, dispensador_Activity::class.java)
        intent.putExtra("key", user.nametoken)
        intent.putExtra("token", user.value)
        intent.putExtra("email", user.email )
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }
    fun irPrestamos(view:View){
        val user = this.dbConexion.GetCurrentUser()
        val intent = Intent(this, prestamos_Activity::class.java)
        intent.putExtra("key", user.nametoken)
        intent.putExtra("token", user.value)
        intent.putExtra("email", user.email )
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }

    fun irAnunciones(view:View){

        val user = this.dbConexion.GetCurrentUser()
        val intent = Intent(this, anunciones_Activity::class.java)
        intent.putExtra("key", user.nametoken)
        intent.putExtra("token", user.value)
        intent.putExtra("email", user.email )
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }
}
