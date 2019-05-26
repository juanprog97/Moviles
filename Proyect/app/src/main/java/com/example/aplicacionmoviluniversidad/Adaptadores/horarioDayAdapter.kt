package com.example.aplicacionmoviluniversidad.Adaptadores

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.example.aplicacionmoviluniversidad.Modelos.DiaClase
import com.example.aplicacionmoviluniversidad.Modelos.Horario
import com.example.aplicacionmoviluniversidad.R

class horarioDayAdapter(var context: Context,day:List<DiaClase>,Horario:List<List<Horario>>): BaseExpandableListAdapter() {
    private var Horario = ArrayList<ArrayList<Horario>>()
    private var day = ArrayList<DiaClase>()
    init{
        this.Horario =Horario as   ArrayList<ArrayList<Horario>>
        this.day = day as ArrayList<DiaClase>
    }
    override fun getGroup(groupPosition: Int): Any {
        return day[groupPosition]
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return Horario[groupPosition].size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return Horario[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        var color = mutableListOf<String>("#3598db","#9a59b5","#f1c40f","#e67f22","#e84c3d","#34495e")
        if(convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.item_base_horario, null)


        }
        var nombre = convertView!!.findViewById<TextView>(R.id.asig)
        var hora = convertView!!.findViewById<TextView>(R.id.hora)
        var salon  = convertView!!.findViewById<TextView>(R.id.sala)
        var fon = convertView!!.findViewById<LinearLayout>(R.id.fondo)
        nombre.text = Horario[groupPosition][childPosition].nombre
        hora.text = Horario[groupPosition][childPosition].hora
        salon.text = Horario[groupPosition][childPosition].salon
        if(childPosition == 0)
            fon.setBackgroundResource(R.drawable.ic_base)
        else if(childPosition==1)
            fon.setBackgroundResource(R.drawable.ic_base2)
        else if(childPosition==2)
            fon.setBackgroundResource(R.drawable.ic_base4)
        else if(childPosition==3)
            fon.setBackgroundResource(R.drawable.ic_base3)
        else if(childPosition==4)
            fon.setBackgroundResource(R.drawable.ic_base6)
        else if(childPosition==5)
            fon.setBackgroundResource(R.drawable.ic_base5)


        return convertView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }
    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }

    override fun hasStableIds(): Boolean {
        return true
    }
    override fun getGroupCount(): Int {
        return day.size
    }
    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        if(convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.schedule_header, null)
        }
        var nombreCurso =convertView!!.findViewById<TextView>(R.id.dia)
        nombreCurso.text = day[groupPosition].diaN

        return convertView
    }

}