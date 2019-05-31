package com.example.aplicacionmoviluniversidad.Adaptadores

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.aplicacionmoviluniversidad.Modelos.Dispensador
import com.example.aplicacionmoviluniversidad.R
import org.w3c.dom.Text


class listDispensadorAdapter(private val activity: Activity, Dispensador:List<Dispensador>?): BaseAdapter() {
    private var Dispensador = ArrayList<Dispensador>()
    init{
        this.Dispensador = Dispensador as ArrayList<Dispensador>
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var vi: View
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        vi = inflater.inflate(R.layout.item_dispensador, null)
        val nombre = vi.findViewById<TextView>(R.id.nombreCurso)
        val numero = vi.findViewById<TextView>(R.id.num)
        val logo = vi.findViewById<ImageView>(R.id.imageC)
        nombre.text = Dispensador[position].nombreLugar
        numero.text = Dispensador[position].numDisponible.toString()
        if( Dispensador[position].numDisponible!! > 0 ){
            logo.setImageResource(R.mipmap.ic_localsi_)
        }
        else{
            logo.setImageResource(R.mipmap.ic_localno_)
        }

        return vi
    }

    override fun getItem(position: Int): Any {
        return Dispensador[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return Dispensador.size
    }

}