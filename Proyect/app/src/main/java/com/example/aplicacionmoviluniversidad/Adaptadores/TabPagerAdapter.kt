package com.example.aplicacionmoviluniversidad.Adaptadores

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.aplicacionmoviluniversidad.Controladores.events_Activity
import com.example.aplicacionmoviluniversidad.Controladores.notices_Activity

class TabPagerAdapter(fm:FragmentManager,  private var tabCount: Int): FragmentPagerAdapter(fm){

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return notices_Activity()
            1 -> return events_Activity()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

}