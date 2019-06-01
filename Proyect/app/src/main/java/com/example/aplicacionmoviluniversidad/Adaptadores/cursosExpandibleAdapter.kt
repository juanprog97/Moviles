package com.example.aplicacionmoviluniversidad.Adaptadores

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.aplicacionmoviluniversidad.Modelos.Curso
import com.example.aplicacionmoviluniversidad.Modelos.Nota
import com.example.aplicacionmoviluniversidad.R

class cursosExpandibleAdapter(var context: Context, var CursoList : List<Curso>) : BaseExpandableListAdapter(){


    private var cursoArray = ArrayList<Curso>()
    init{
        this.cursoArray = CursoList as ArrayList<Curso>
    }
    override fun getChildrenCount(groupPosition: Int): Int {
        return 1
    }
    override fun getGroup(groupPosition: Int): Any {
        return CursoList[groupPosition]
    }
    fun getNotas(groupPosition: Int): List<Nota>?{
        return CursoList[groupPosition].parciales
    }
    fun getNombre(groupPosition: Int):String?{
        return CursoList[groupPosition].nombreCurs
    }
    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
       return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if(convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.layout_group, null)
        }
        var nombreCurso =convertView!!.findViewById<TextView>(R.id.nombreCurso)
        nombreCurso.text =CursoList[groupPosition].nombreCurs

        return convertView
    }


    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return CursoList[groupPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }



    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView

        if(convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.layout_child, null)


        }
        var codA = convertView!!.findViewById<TextView>(R.id.codAsign)
        var porcei = convertView!!.findViewById<TextView>(R.id.porcentajeina)
        var notaA  = convertView!!.findViewById<TextView>(R.id.notaAcum)
        codA.text = CursoList[groupPosition].codAsignatura
        porcei.text = CursoList[groupPosition].porcentInam
        notaA.text = CursoList[groupPosition].notaAcumulada

        return convertView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return CursoList.size //To change body of created functions use File | Settings | File Templates.
    }

}