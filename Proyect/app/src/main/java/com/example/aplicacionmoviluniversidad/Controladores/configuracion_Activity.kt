package com.example.aplicacionmoviluniversidad.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.example.aplicacionmoviluniversidad.R
import com.example.aplicacionmoviluniversidad.Servicios.UserDBService
import kotlinx.android.synthetic.main.dialog_notas.view.*

class configuracion_Activity : AppCompatActivity() {
    private var dbConexion: UserDBService = UserDBService(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion_)
    }
    fun regresar(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun salir(view: View){
        dbConexion.eliminateUser()
        val intent = Intent(this, login_Activity::class.java)
        startActivity(intent)
    }

    fun navTerminosCondiciones(view : View){
        Log.d("DIALOG","Opening Dialog of TerminosyCondiciones")
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_term_cond, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
        val mAlertDialog = mBuilder.show()
        mDialogView.cerrar.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }

    }

    fun navInfoApp(view: View){
        Log.d("DIALOG","Opening Dialog of IngormaciónDeApp")
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_info_app, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
        val mAlertDialog = mBuilder.show()
        mDialogView.cerrar.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }
    }
}
