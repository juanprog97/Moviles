package com.example.datastorage.Adapters

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.datastorage.Modelos.User
import com.example.datastorage.R

class UsersListAdapter(private val activity: Activity, usersList: List<User>?) : BaseAdapter(){
    private var usersList = ArrayList<User>()

    init {
        this.usersList = usersList as ArrayList<User>
    }

    override fun getCount(): Int {
        return usersList.size
    }

    override fun getItem(i: Int): Any {
        return usersList[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    fun getName(i: Int): String? {
        return usersList[i].name
    }

    fun getEmail(i: Int): String? {
        return usersList[i].email
    }

    fun getAge(i: Int): String? {
        return usersList[i].age.toString()
    }

    fun getImages(i:Int): ByteArray?{
        return  usersList[i].images
    }


    override fun getView(i: Int, convertView: View?, viewGroup: ViewGroup): View {
        var vi: View
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        vi = inflater.inflate(R.layout.row_item, null)
        val nombre = vi.findViewById<TextView>(R.id.Nombre)
        val edad = vi.findViewById<TextView>(R.id.Edad)
        val image = vi.findViewById<ImageView>(R.id.userImage)
        val imate : Bitmap = BitmapFactory.decodeByteArray(usersList[i].images, 0, usersList[i]?.images?.size!!)
        image.setImageBitmap(imate)
        nombre.text = usersList[i].name
        edad.text = usersList[i].age.toString()
        return vi
    }
}