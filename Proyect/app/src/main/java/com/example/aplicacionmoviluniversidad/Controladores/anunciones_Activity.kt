package com.example.aplicacionmoviluniversidad.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ImageView
import com.example.aplicacionmoviluniversidad.Adaptadores.TabPagerAdapter
import com.example.aplicacionmoviluniversidad.R

class anunciones_Activity : AppCompatActivity() {
    private lateinit var correo : String
    private lateinit var key : String
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        this.correo = intent.getStringExtra("email")
        this.key = intent.getStringExtra("key")
        this.token = intent.getStringExtra("token")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anunciones_)
        configureTabLayout()

    }

    private fun configureTabLayout() {


        val tab_layout = findViewById<TabLayout>(R.id.tab_anuncios)
        tab_layout.addTab(tab_layout.newTab().setCustomView(R.layout.icontab1))
        tab_layout.addTab(tab_layout.newTab().setCustomView(R.layout.icontab2))

        val adapter = TabPagerAdapter(supportFragmentManager,tab_layout.tabCount)
        val viever = findViewById<ViewPager>(R.id.viewA)
        viever.adapter = adapter

        viever.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        tab_layout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viever.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }

    fun regresar(view: View){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("key", this.key)
        intent.putExtra("token", this.token)
        intent.putExtra("email",this.correo )
        startActivity(intent)

    }


}
