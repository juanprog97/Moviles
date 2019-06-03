package com.example.aplicacionmoviluniversidad.Adaptadores

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.aplicacionmoviluniversidad.Modelos.Anuncio
import com.example.aplicacionmoviluniversidad.R
import com.squareup.picasso.Picasso

class listEventosAdapter(anuncios : List<Anuncio>) : RecyclerView.Adapter<listEventosAdapter.ViewHolder>(){
    private var anuncios = ArrayList<Anuncio>()
    init{
        this.anuncios = anuncios as ArrayList<Anuncio>

    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): listEventosAdapter.ViewHolder {
        val vista = LayoutInflater.from(p0?.context).inflate(R.layout.item_events,p0,false)
        return ViewHolder(vista)

    }

    override fun getItemCount(): Int {
        return anuncios.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.title.text = anuncios[p1].title
        if(anuncios[p1].field_image_box == ""){
            Picasso.get()
                .load("www.jsasa.com")
                .placeholder(R.drawable.loading)
                .error(R.drawable.nodisponible)
                .into(p0.image)
        }
        else{
            Picasso.get()
                .load(anuncios[p1].field_image_box)
                .placeholder(R.drawable.loading)
                .error(R.drawable.nodisponible)
                .into(p0.image)
        }

    }

    class ViewHolder(vista: View): RecyclerView.ViewHolder(vista){
        val title = vista.findViewById<TextView>(R.id.tituloEvento)
        val image = vista.findViewById<ImageView>(R.id.imageEvent)
    }

}