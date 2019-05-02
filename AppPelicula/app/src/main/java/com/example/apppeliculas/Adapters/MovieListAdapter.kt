package com.example.apppeliculas.Adapters

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
import com.example.apppeliculas.R
import com.example.apppeliculas.Modelos.Movie




class MovieListAdapter(private val activity: Activity, MovieList: List<Movie>?) : BaseAdapter(){
    private var MovieList = ArrayList<Movie>()
    init {
        this.MovieList = MovieList as ArrayList<Movie>
    }

    override fun getCount(): Int {
        return MovieList.size
    }

    override fun getItem(i: Int): Any {
        return MovieList[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    fun getName(i: Int): String? {
        return MovieList[i].title
    }

    fun getYear(i: Int): String? {
        return MovieList[i].year.toString()
    }

    fun getSinopsis(i: Int): String? {
        return MovieList[i].sinopsis
    }

    fun getImages(i:Int): ByteArray?{
        return  MovieList[i].images
    }

    fun getIdPelicula(i:Int): Int?{
        return MovieList[i].idPeli
    }

    fun getReserva(i:Int): String?{
        return  MovieList[i].reserva
    }



    override fun getView(i: Int, convertView: View?, viewGroup: ViewGroup): View {
        var vi: View
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        vi = inflater.inflate(R.layout.row_item, null)
        val nombre = vi.findViewById<TextView>(R.id.name)
        val edad = vi.findViewById<TextView>(R.id.year)
        val image = vi.findViewById<ImageView>(R.id.userImage)
        val reserva = vi.findViewById<TextView>(R.id.reserva)
        val imate : Bitmap = BitmapFactory.decodeByteArray(MovieList[i].images, 0, MovieList[i]?.images?.size!!)
        image.setImageBitmap(imate)
        nombre.text = MovieList[i].title
        edad.text = MovieList[i].year.toString()


        if(MovieList[i].reserva == "no"){
            reserva.text  = "No Reservado"
        }
        else{
            reserva.text = "Reserva"
        }
        return vi
    }
}