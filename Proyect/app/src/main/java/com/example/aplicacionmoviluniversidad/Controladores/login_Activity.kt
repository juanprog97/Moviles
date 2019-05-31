package com.example.aplicacionmoviluniversidad.Controladores

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.aplicacionmoviluniversidad.Modelos.UserModel
import com.example.aplicacionmoviluniversidad.R
import com.example.aplicacionmoviluniversidad.Servicios.loginServices

import kotlinx.android.synthetic.main.activity_login_.*
import org.json.JSONObject

class login_Activity : AppCompatActivity() {
    private lateinit var loginServices : loginServices
    override fun onCreate(savedInstanceState: Bundle?) {
        val SDK_INT = android.os.Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        loginServices= loginServices(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_)

    }
    fun test(view: View){
        var user= findViewById<TextView>(R.id.user)
        var pass= findViewById<TextView>(R.id.password)
        var User= UserModel(user.text.toString(),pass.text.toString(),null,null,null,null,null)

        val data = this.loginServices.validate(User)
        println(data)
        val temp = JSONObject(data)
        if( temp.getString("valido")=="true" ){
            User.nombre= temp.getString("nombre")
            User.apellido= temp.getString("apellido")
            User.email= temp.getString("email")
            User.cod= temp.getString("emplid")
            User.toke= temp.getString("x-t6519fdd1s5q")
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("key", "x-t6519fdd1s5q")
            intent.putExtra("token", User.toke)
            intent.putExtra("email",User.email )
            startActivity(intent)
        }else{
            Toast.makeText(this,"El usuario es invalido", Toast.LENGTH_SHORT).show()
        }

    }


}
