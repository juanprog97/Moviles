package com.example.datastorage.Controladores

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.datastorage.Modelos.User
import com.example.datastorage.R
import com.example.datastorage.Servicios.BitMapServices
import com.example.datastorage.Servicios.UserDBServices

class RegisterActivity : AppCompatActivity() {

    private lateinit var image: ByteArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
    fun String.isValidEmail(): Boolean
            = this.isNotEmpty() &&
            Patterns.EMAIL_ADDRESS.matcher(this).matches()


    fun profile(view: View)
    {
        val nombre = findViewById<TextView>(R.id.nombre);
        val correo = findViewById<TextView>(R.id.correo);
        val contra = findViewById<TextView>(R.id.contraseña);
        val edad = findViewById<TextView>(R.id.edad);

        if(TextUtils.isEmpty(nombre.text.toString())==false  &&  TextUtils.isEmpty(correo.text.toString())==false   && TextUtils.isEmpty(contra.text.toString())==false  && TextUtils.isEmpty(edad.text.toString())==false )
        {
            val user = User(null, nombre.text.toString(), correo.text.toString(),edad.text.toString().toInt(), contra.text.toString(),this.image);

            if(user.email!!.isValidEmail())
            {
                if( !UserDBServices(this).verifyUser(user)){
                    nombre.text = ""
                    contra.text = ""
                    correo.text = ""
                    edad.text = ""
                    UserDBServices(this).saveUser(user)
                    Toast.makeText(this, "Se ha Guardado Un Usuario", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "El usuario ya existe, por favor intentelo de nuevo", Toast.LENGTH_SHORT)  .show()
                }

            }
            else{
                Toast.makeText(this, "Correo Mal Escrito", Toast.LENGTH_SHORT)  .show()
            }

           /* val intent = Intent(this, UsersListActivity::class.java)
            startActivity(intent);*/ //
        }else{
            Toast.makeText(this, "Faltan Campos por llenar correctamente",  Toast.LENGTH_SHORT).show()
        }

    }

    fun Cargar(view: View){
        cargarImagen()
    }

    private fun cargarImagen() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.setType("image/");
        startActivityForResult(intent,1)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== RESULT_OK && requestCode== 1 && data!=null){
            val imageUri= data?.data
            val bitmap= MediaStore.Images.Media.getBitmap(this.contentResolver,imageUri)
            this.image= BitMapServices(this).getBytes(bitmap)
            println("esto" + image);

        }
    }

    fun volver(view:View)
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent);
    }

}

