/*package com.example.aplicacionmoviluniversidad.Adaptadores

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.aplicacionmoviluniversidad.Modelos.Nota
import com.example.aplicacionmoviluniversidad.R

class listNotasAdapter(private val activity: Activity, Nota:List<Nota>?) : BaseAdapter(){
    private var Nota = ArrayList<Nota>()
    init{
            this.Nota =Nota as  ArrayList<Nota>
    }
    override fun getItem(position: Int): Any {
         return Nota[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
       return Nota.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var vi: View
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        vi = inflater.inflate(R.layout.item_nota, null)
        val nombreParcial = vi.findViewById<TextView>(R.id.nom)
        val nota = vi.findViewById<TextView>(R.id.nota)
        val porce = vi.findViewById<TextView>(R.id.porcen)
        nombreParcial.text = Nota[position].nombre+": "
        nota.text = Nota[position].nota.toString()
        porce.text = Nota[position].porcentaje.toString()+"%"
        return vi
    }

}*/