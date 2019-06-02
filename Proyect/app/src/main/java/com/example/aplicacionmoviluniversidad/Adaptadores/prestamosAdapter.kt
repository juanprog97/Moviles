package com.example.aplicacionmoviluniversidad.Adaptadores

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.aplicacionmoviluniversidad.Modelos.LibroPrestamo
import com.example.aplicacionmoviluniversidad.R


class prestamosAdapter(private val activity: Activity, prestamos:List<LibroPrestamo> ): BaseAdapter() {
    private var prestamos = ArrayList<LibroPrestamo>()
    init{
        this.prestamos = prestamos as ArrayList<LibroPrestamo>
    }


    override fun getItem(position: Int): Any {
        return prestamos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return prestamos.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var vi: View
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        vi = inflater.inflate(R.layout.item_prestamo, null)
        val nombreTitulo = vi.findViewById<TextView>(R.id.nombreTitulo)
        val isba = vi.findViewById<TextView>(R.id.isb)
        val ubi = vi.findViewById<TextView>(R.id.ubicacion)
        val multa = vi.findViewById<TextView>(R.id.valorMulta)
        nombreTitulo.text = prestamos[position].titu
        isba. text = prestamos[position].stopo
        ubi.text = prestamos[position].loca
        multa.text = prestamos[position].mult.toString()
        return vi
    }
}