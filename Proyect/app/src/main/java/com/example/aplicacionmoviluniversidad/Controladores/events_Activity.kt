package com.example.aplicacionmoviluniversidad.Controladores

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aplicacionmoviluniversidad.Adaptadores.listEventosAdapter
import com.example.aplicacionmoviluniversidad.Adaptadores.listNoticiasAdapter
import com.example.aplicacionmoviluniversidad.R
import com.example.aplicacionmoviluniversidad.Servicios.anunciosServices


class events_Activity : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val vi: View
        vi = inflater?.inflate(R.layout.fragment_events_, container, false)
        val recycle = vi.findViewById<RecyclerView>(R.id.eventos)
        recycle.layoutManager = LinearLayoutManager(context)
        val Serviceanuncio = anunciosServices(context!!)
        var anuncios = Serviceanuncio.desplegarAnuncios()
        var anuncioEventos = Serviceanuncio.extraerEventos(anuncios.rows)
        recycle.adapter = listEventosAdapter(anuncioEventos)
        return vi
    }

}
